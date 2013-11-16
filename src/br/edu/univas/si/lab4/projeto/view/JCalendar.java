package br.edu.univas.si.lab4.projeto.view;

import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.plaf.synth.SynthComboBoxUI;
import javax.swing.text.MaskFormatter;

public class JCalendar extends JComboBox<Object> {

	private static final long serialVersionUID = 1L;
	private boolean showActualDate;
	private DatePopup datePopup;
	private JFormattedTextField formattedTextField;

  public JCalendar() {
    this(true);
  }

  public JCalendar(boolean showActualDate) {
    super();
    this.showActualDate = showActualDate;
    MaskFormatter formatter = null;
    try {
      formatter = new MaskFormatter("##/##/####");
      formatter.setPlaceholderCharacter('_');
    } catch (ParseException e) {
    }

    formattedTextField = new JFormattedTextField(formatter);
    formattedTextField.setBorder(((JComponent) getEditor().getEditorComponent()).getBorder());
    if (showActualDate) {
      formattedTextField.setValue(getTime());
    } else {
      formattedTextField.setValue("");
    }

    formattedTextField.addFocusListener(new FocusAdapter() {

      @Override
      public void focusLost(FocusEvent evt) {
        setValue();
      }
    });

    setEditor(new BasicComboBoxEditor() {

      @Override
      public Component getEditorComponent() {
        return formattedTextField;
      }
    });
    super.setEditable(true);
  }

  private String getTime() {
    return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
  }

  private boolean isValidDate(String source) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      sdf.setLenient(false);
      sdf.parse(source);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

  private class MetalDateComboBoxUI extends MetalComboBoxUI {

    @Override
    protected ComboPopup createPopup() {
      datePopup = new DatePopup();
      return datePopup;
    }
  }

  private class WindowsDateComboBoxUI extends WindowsComboBoxUI {

    @Override
    protected ComboPopup createPopup() {
      datePopup = new DatePopup();
      return datePopup;
    }
  }

  private class MotifDateComboBoxUI extends MotifComboBoxUI {

    
	private static final long serialVersionUID = 1L;

	@Override
    protected ComboPopup createPopup() {
      datePopup = new DatePopup();
      return datePopup;
    }
  }

  private class SynthDateComboBoxUI extends SynthComboBoxUI {

    @Override
    protected ComboPopup createPopup() {
      datePopup = new DatePopup();
      return datePopup;
    }
  }

  private class DatePopup extends BasicComboPopup {

	private static final long serialVersionUID = 1L;
	private DatePanel datePanel;

    public DatePopup() {
      super(JCalendar.this);
      removeAll();
    }

    public void constructDatePopup() {
      removeAll();
      setPreferredSize(new Dimension(193, 142));
      setBorder(BorderFactory.createLineBorder(Color.BLACK));
      setLayout(new GridLayout(1, 1));
      datePanel = new DatePanel();
      if (hasValidDate()) {
        datePanel.prepareDate();
      } else {
        datePanel.prepare(false, false, false, false);
      }
      add(datePanel);
    }

    @Override
    public void show(Component invoker, int x, int y) {
      constructDatePopup();
      super.show(JCalendar.this, JCalendar.this.getWidth() - 194, JCalendar.this.getHeight());
    }

    @Override
    public void setVisible(boolean b) {
      if (b == false) {
        setValue();
        datePanel.stopTimer();
        datePanel = null;
      }

      super.setVisible(b);
    }
  }

  private class DatePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GregorianCalendar gc;
    private Timer timer;
    private JLabel[] day;
    private String[] month;
    private JLabel selectedLabel;
    private int timerFlag = 0;
    private int dayOfMonth = 0;
    private int selectedDay = 0;
    private boolean pressed = false;
    private static final int TIME_FROZEN = 3;

    public DatePanel() {
      initComponents();
      gc = new GregorianCalendar();

      nextYear.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent evt) {
          nextYear.setBorder(BorderFactory.createLoweredBevelBorder());
          if (SwingUtilities.isLeftMouseButton(evt)) {
            initTimer(true, false, true, false);
          }
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
          nextYear.setBorder(BorderFactory.createRaisedBevelBorder());

          if (SwingUtilities.isLeftMouseButton(evt)) {
            terminateTimer();
            if (timerFlag <= TIME_FROZEN) {
              prepare(true, false, true, false);
            }
            timerFlag = 0;
          }
        }
      });

      nextMonth.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent evt) {
          nextMonth.setBorder(BorderFactory.createLoweredBevelBorder());
          if (SwingUtilities.isLeftMouseButton(evt)) {
            initTimer(false, true, true, false);
          }
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
          nextMonth.setBorder(BorderFactory.createRaisedBevelBorder());

          if (SwingUtilities.isLeftMouseButton(evt)) {
            terminateTimer();
            if (timerFlag <= TIME_FROZEN) {
              prepare(false, true, true, false);
            }
            timerFlag = 0;
          }
        }
      });

      previousMonth.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent evt) {
          previousMonth.setBorder(BorderFactory.createLoweredBevelBorder());
          if (SwingUtilities.isLeftMouseButton(evt)) {
            initTimer(false, true, false, false);
          }
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
          previousMonth.setBorder(BorderFactory.createRaisedBevelBorder());

          if (SwingUtilities.isLeftMouseButton(evt)) {
            terminateTimer();
            if (timerFlag <= TIME_FROZEN) {
              prepare(false, true, false, false);
            }
            timerFlag = 0;
          }
        }
      });

      previousYear.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent evt) {
          previousYear.setBorder(BorderFactory.createLoweredBevelBorder());
          if (SwingUtilities.isLeftMouseButton(evt)) {
            initTimer(true, false, false, false);
          }
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
          previousYear.setBorder(BorderFactory.createRaisedBevelBorder());

          if (SwingUtilities.isLeftMouseButton(evt)) {
            terminateTimer();
            if (timerFlag <= TIME_FROZEN) {
              prepare(true, false, false, false);
            }
            timerFlag = 0;
          }
        }
      });

      month = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
      day = new JLabel[42];
    }
                      
    private void initComponents() {
      navegatePanel = new JPanel();
      previousYear = new JLabel();
      previousMonth = new JLabel();
      dateLabel = new JLabel();
      nextMonth = new JLabel();
      nextYear = new JLabel();
      weekAndDaysPanel = new JPanel();
      weekPanel = new JPanel();
      sundayLabel = new JLabel();
      mondayLabel = new JLabel();
      tuesdayLabel = new JLabel();
      wednesdayLabel = new JLabel();
      thursdayLabel = new JLabel();
      fridayLabel = new JLabel();
      saturdayLabel = new JLabel();
      daysPanel = new JPanel();

      setLayout(new BorderLayout());

      navegatePanel.setLayout(null);

      navegatePanel.setPreferredSize(new Dimension(20, 20));
      previousYear.setFont(new Font("Arial", 0, 9));
      previousYear.setText("<<");
      previousYear.setHorizontalAlignment(SwingConstants.CENTER);
      previousYear.setBorder(BorderFactory.createRaisedBevelBorder());

      navegatePanel.add(previousYear);
      previousYear.setBounds(0, 0, 20, 20);

      previousMonth.setFont(new Font("Arial", 0, 9));
      previousMonth.setText("<");
      previousMonth.setHorizontalAlignment(SwingConstants.CENTER);
      previousMonth.setBorder(BorderFactory.createRaisedBevelBorder());

      navegatePanel.add(previousMonth);
      previousMonth.setBounds(20, 0, 20, 20);

      dateLabel.setFont(new Font("Tahoma", 1, 11));
      dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
      dateLabel.setText("Abril, 2006");
      navegatePanel.add(dateLabel);
      dateLabel.setBounds(44, 3, 105, 14);

      nextMonth.setFont(new Font("Arial", 0, 9));
      nextMonth.setText(">");
      nextMonth.setHorizontalAlignment(SwingConstants.CENTER);
      nextMonth.setBorder(BorderFactory.createRaisedBevelBorder());

      navegatePanel.add(nextMonth);
      nextMonth.setBounds(151, 0, 20, 20);

      nextYear.setFont(new Font("Arial", 0, 9));
      nextYear.setText(">>");
      nextYear.setHorizontalAlignment(SwingConstants.CENTER);
      nextYear.setBorder(BorderFactory.createRaisedBevelBorder());

      navegatePanel.add(nextYear);
      nextYear.setBounds(171, 0, 20, 20);

      add(navegatePanel, BorderLayout.NORTH);

      weekAndDaysPanel.setLayout(new BorderLayout());

      weekAndDaysPanel.setBorder(BorderFactory.createEtchedBorder());
      weekPanel.setLayout(new GridLayout(1, 7));

      weekPanel.setBorder(BorderFactory.createEtchedBorder());
      weekPanel.setPreferredSize(new Dimension(20, 20));
      sundayLabel.setForeground(new Color(255, 0, 0));
      sundayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      sundayLabel.setText("D");
      weekPanel.add(sundayLabel);

      mondayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      mondayLabel.setText("S");
      weekPanel.add(mondayLabel);

      tuesdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      tuesdayLabel.setText("T");
      weekPanel.add(tuesdayLabel);

      wednesdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      wednesdayLabel.setText("Q");
      weekPanel.add(wednesdayLabel);

      thursdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      thursdayLabel.setText("Q");
      weekPanel.add(thursdayLabel);

      fridayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      fridayLabel.setText("S");
      weekPanel.add(fridayLabel);

      saturdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      saturdayLabel.setText("S");
      weekPanel.add(saturdayLabel);

      weekAndDaysPanel.add(weekPanel, BorderLayout.NORTH);

      daysPanel.setLayout(new GridLayout(6, 7));

      daysPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
      
      daysPanel.setPreferredSize(new Dimension(95, 95));
      weekAndDaysPanel.add(daysPanel, BorderLayout.CENTER);

      add(weekAndDaysPanel, java.awt.BorderLayout.CENTER);

    }                      

    private void initTimer(final boolean b1, final boolean b2, final boolean b3, final boolean b4) {
      timer = new Timer(100, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent evt) {
          if (timerFlag > TIME_FROZEN) {
            prepare(b1, b2, b3, b4);
          } else {
            timerFlag++;
          }
        }
      });

      timer.start();
    }

    private void terminateTimer() {
      timer.stop();
      timer = null;
    }

    private void initArrayOfDays() {
      for (int i = 0; i < 42; i++) {
        day[i] = new JLabel();
        day[i].setHorizontalAlignment(SwingConstants.CENTER);
        day[i].setOpaque(true);
        day[i].setBackground(Color.WHITE);
      }
    }

    private void fillDaysPanel(int firstDayOfMonth, int maximumDayOfMonth) {
      int index = 1;
      daysPanel.removeAll();
      initArrayOfDays();

      for (int i = 0; i < 42; i++) {
        if (i + 1 >= firstDayOfMonth && index <= maximumDayOfMonth) {
          day[i].setText(String.valueOf(index));
          day[i].setBorder(BorderFactory.createEmptyBorder());

          if (index == selectedDay || index == dayOfMonth) {
            day[i].setBorder(BorderFactory.createLoweredBevelBorder());

            if (index == selectedDay) {
              day[i].setBackground(Color.GRAY);
              day[i].setForeground(Color.WHITE);
              selectedLabel = day[i];
            } else {
              if (i % 7 != 0) {
                day[i].setForeground(UIManager.getDefaults().getColor("label.foreground"));
              } else {
                day[i].setForeground(Color.RED);
              }
            }
          } else {
            if (day[i].getText().equals(String.valueOf(selectedDay))) {
              day[i].setBackground(Color.GRAY);
              day[i].setForeground(Color.WHITE);
              selectedLabel = day[i];
            }

            if (day[i] != selectedLabel) {
              if (i % 7 != 0) {
                day[i].setForeground(UIManager.getDefaults().getColor("label.foreground"));
              } else {
                day[i].setForeground(Color.RED);
              }
            }
          }

          final int finalI = i;
          index++;

          day[i].addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
              if (SwingUtilities.isLeftMouseButton(evt)) {
                configureDayLabels(day[finalI]);
                pressed = true;
              }
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
              if (SwingUtilities.isLeftMouseButton(evt) && pressed) {
                configureDayLabels(day[finalI]);
              }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
              if (SwingUtilities.isLeftMouseButton(evt)) {
                if (getMousePosition() != null) {
                  datePopup.hide();
                  DecimalFormat df = new DecimalFormat("00");
                  String dayText = df.format(selectedDay);
                  String monthText = df.format(gc.get(Calendar.MONTH) + 1);
                  String yearText = String.valueOf(gc.get(Calendar.YEAR));
                  formattedTextField.setValue(dayText + "/" + monthText + "/" + yearText);
                }

                pressed = false;
              }
            }
          });
        }

        daysPanel.add(day[i]);
      }
    }

    private void setDaysAndFill() {
      gc.set(Calendar.DAY_OF_MONTH, 1);
      int firstDayOfMonth = gc.get(Calendar.DAY_OF_WEEK);
      int maximumDayOfMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
      gc.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      String monthText = month[gc.get(Calendar.MONTH)];
      dateLabel.setText(String.valueOf(monthText + ", " + gc.get(Calendar.YEAR)));
      fillDaysPanel(firstDayOfMonth, maximumDayOfMonth);
    }

    private void configureDayLabels(JLabel day) {
      if (!selectedLabel.getText().equals(String.valueOf(dayOfMonth))) {
        selectedLabel.setBorder(BorderFactory.createEmptyBorder());
      }

      selectedLabel.setBackground(Color.WHITE);

      gc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(selectedLabel.getText()));
      if (gc.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
        selectedLabel.setForeground(UIManager.getDefaults().getColor("label.foreground"));
      } else {
        selectedLabel.setForeground(Color.RED);
      }
      gc.set(Calendar.DAY_OF_MONTH, dayOfMonth);

      day.setBorder(BorderFactory.createLoweredBevelBorder());
      day.setBackground(Color.GRAY);
      day.setForeground(Color.WHITE);
      selectedLabel = day;
      selectedDay = Integer.parseInt(selectedLabel.getText());
    }

    public void prepare(boolean rollYear, boolean rollMonth, boolean up, boolean instantiateGc) {
      if (instantiateGc) {
        gc = new GregorianCalendar();
      }
      dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
      if (selectedDay == 0 || instantiateGc) {
        selectedDay = dayOfMonth;
      }
      if (rollYear) {
        gc.roll(Calendar.YEAR, up);
      }

      if (rollMonth) {
        if ((up && gc.get(Calendar.MONTH) == Calendar.DECEMBER) || (!up && gc.get(Calendar.MONTH) == Calendar.JANUARY)) {
          gc.roll(Calendar.YEAR, up);
        }
        gc.roll(Calendar.MONTH, up);
      }

      setDaysAndFill();
    }

    public void prepareDate() {
      if (hasValidDate()) {
        int d = Integer.parseInt(formattedTextField.getValue().toString().substring(0, 2));
        int m = Integer.parseInt(formattedTextField.getValue().toString().substring(3, 5)) - 1;
        int y = Integer.parseInt(formattedTextField.getValue().toString().substring(6));
        dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        selectedDay = d;
        gc.set(Calendar.MONTH, m);
        gc.set(Calendar.YEAR, y);
        setDaysAndFill();
      } else {
        prepare(false, false, false, true);
      }
    }

    public void stopTimer() {
      if (timer != null) {
        timer.stop();
      }
    }
    private JLabel dateLabel;
    private JPanel daysPanel;
    private JLabel fridayLabel;
    private JLabel mondayLabel;
    private JPanel navegatePanel;
    private JLabel nextMonth;
    private JLabel nextYear;
    private JLabel previousMonth;
    private JLabel previousYear;
    private JLabel saturdayLabel;
    private JLabel sundayLabel;
    private JLabel thursdayLabel;
    private JLabel tuesdayLabel;
    private JLabel wednesdayLabel;
    private JPanel weekAndDaysPanel;
    private JPanel weekPanel;
  }

  private void setValue() {
    if (isValidDate(formattedTextField.getText())) {
      formattedTextField.setValue(formattedTextField.getText());
    } else {
      formattedTextField.setText(formattedTextField.getValue().toString());
    }
  }

  @Override
  public void updateUI() {
    ComboBoxUI comboBoxUI = (ComboBoxUI) UIManager.getUI(JCalendar.this);
    if (comboBoxUI instanceof MetalComboBoxUI) {
      comboBoxUI = new MetalDateComboBoxUI();
    } else if (comboBoxUI instanceof MotifComboBoxUI) {
      comboBoxUI = new MotifDateComboBoxUI();
    } else if (comboBoxUI instanceof WindowsComboBoxUI) {
      comboBoxUI = new WindowsDateComboBoxUI();
    } else if (comboBoxUI instanceof SynthComboBoxUI) {
      comboBoxUI = new SynthDateComboBoxUI();
    }

    setUI(comboBoxUI);
  }

  @Override
  public boolean selectWithKeyChar(char keyChar) {
    return false;
  }

  @Override
  public void setEditable(boolean aFlag) {
    if (!aFlag) {
      throw new IllegalArgumentException("no sense in setting editable mode to false");
    }
  }

  @Override
  public Object getSelectedItem() {
    try {
      super.setSelectedItem(null);
      int d = Integer.parseInt(formattedTextField.getValue().toString().substring(0, 2));
      int m = Integer.parseInt(formattedTextField.getValue().toString().substring(3, 5)) - 1;
      int y = Integer.parseInt(formattedTextField.getValue().toString().substring(6));
      GregorianCalendar gc = new GregorianCalendar();
      gc.set(Calendar.DAY_OF_MONTH, d);
      gc.set(Calendar.MONTH, m);
      gc.set(Calendar.YEAR, y);
      return gc;
    } catch (StringIndexOutOfBoundsException ex) {
      return null;
    }
  }

  @Override
  public void setSelectedItem(Object anObject) {
    if (anObject != null) {
      if (anObject instanceof GregorianCalendar) {
        String dayText = String.valueOf(((GregorianCalendar) anObject).get(Calendar.DAY_OF_MONTH));
        String monthText = String.valueOf(((GregorianCalendar) anObject).get(Calendar.MONTH));
        String yearText = String.valueOf(((GregorianCalendar) anObject).get(Calendar.YEAR));
        formattedTextField.setValue(dayText + "/" + monthText + "/" + yearText);
      } else {
        super.setSelectedItem(null);
        throw new IllegalArgumentException("invalid date");
      }
    } else {
      if (showActualDate) {
        formattedTextField.setValue(getTime());
      } else {
        formattedTextField.setValue("");
      }
    }

    super.setSelectedItem(null);
  }
  
  public void setSelectedItemString(String string) {
    if (string != null) {

        formattedTextField.setValue(string);
    } else {
        super.setSelectedItem(null);
        throw new IllegalArgumentException("invalid date");
    }
     
  }

  public boolean hasValidDate() {
    return isValidDate(formattedTextField.getValue().toString());
  }

  public String getText() {
    return !formattedTextField.getText().equals("__/__/____") ? formattedTextField.getText() : "";
  }
}
