import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Stopwatch implements ActionListener{

    JFrame frame = new JFrame();
    JButton startButton = new JButton();
    JButton resetButton = new JButton();
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_String = String.format("%02d", seconds);
    String minutes_String = String.format("%02d", minutes);
    String hours_String = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            elapsedTime+= 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;

            String seconds_String = String.format("%02d", seconds);
            String minutes_String = String.format("%02d", minutes);
            String hours_String = String.format("%02d", hours);

            timeLabel.setText(hours_String+":"+ minutes_String+":"+seconds_String);


        }
    });

    Stopwatch(){
        timeLabel.setText(hours_String+":"+ minutes_String+":"+seconds_String);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100, 200, 100, 50);
        startButton.setText("START");
        startButton.setFont(new Font("Ink Free",Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200, 200, 100, 50);
        resetButton.setText("RESET");
        resetButton.setFont(new Font("Ink Free",Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startButton) {
            if (!started) {
                started = true;
                startButton.setText("STOP");
                start();
            }
            else {
                started=false;
                startButton.setText("START");
                stop();
            }
        }
        if (started) {
            if (e.getSource() == resetButton) {
                reset();
                start();
            }
        }
        if (e.getSource() == resetButton && !started){
            startButton.setText("START");
            reset();
        }

    }
    void start() {
        timer.start();
    }
    void stop() {
        timer.stop();
    }
    void reset() {

        timer.stop();
        elapsedTime=0;
        seconds=0;
        minutes=0;
        hours=0;
        String seconds_String = String.format("%02d", seconds);
        String minutes_String = String.format("%02d", minutes);
        String hours_String = String.format("%02d", hours);
        timeLabel.setText(hours_String+":"+ minutes_String+":"+seconds_String);

    }
}
