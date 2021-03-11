package program;

import objects.Berge;
import objects.Update;
import objects.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("DuplicatedCode")
public class MainFrame extends JFrame {
    private final int amount = 3;
    private JButton startButton;
    private JButton endButton;
    private JPanel panel;
    private JTextField text;
    private JLabel right1;
    private JLabel left1;
    private JLabel bridgemiddle;
    private JPanel upPanel;
    private JPanel downPanel;
    private JLabel left2;
    private JLabel left3;
    private JLabel right3;
    private JLabel right2;
    private JLabel bridgedown;
    private JLabel bridgeup;
    private JLabel down3;
    private JLabel down1;
    private JLabel down2;
    private JLabel up2;
    private JLabel up1;
    private JLabel up3;
    private JTextPane textPane1;
    private Berge b1, b2, b3;
    private Vehicle v1, v2, v3;


    public MainFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(500, 300, 700, 500);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                create_threads();
                try {
                    run_thread(Integer.parseInt(text.getText()));
                } catch (Exception l) {
                    System.out.println("dozwolone tylko 1,2 lub 3");
                }


            }
        });
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close_threads();

            }
        });


        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setContentPane(new MainFrame().panel);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void create_threads() {

        Bridge bridge = new Bridge();
        b1 = new Berge("b1", bridge);
        b1.setshow(
                new Update() {
                    @Override
                    public void show(String situation) {
                        if (situation == "left") {
                            bridgemiddle.setText("[    ]");
                            left1.setText(b1.getName());
                        }
                        if (situation == "middle") {
                            left1.setText("~~");
                            right1.setText("~~");
                            bridgemiddle.setText(b1.getName());
                        }
                        if (situation == "right") {
                            bridgemiddle.setText("[    ]");
                            right1.setText(b1.getName());
                        }
                        if (situation == "waitleft") {
                            right1.setText("~~");
                            left1.setText("|");
                        }
                        if (situation == "waitright") {
                            right1.setText("|");
                            left1.setText("~~");
                        }
                    }
                }
        );

        b2 = new Berge("b2", bridge);
        b2.setshow(
                new Update() {
                    @Override
                    public void show(String situation) {
                        if (situation == "left") {
                            bridgemiddle.setText("[    ]");
                            left2.setText(b2.getName());
                        }
                        if (situation == "middle") {
                            left2.setText("~~");
                            right2.setText("~~");
                            bridgemiddle.setText(b2.getName());
                        }
                        if (situation == "right") {
                            bridgemiddle.setText("[    ]");
                            right2.setText(b2.getName());
                        }
                        if (situation == "waitleft") {
                            right2.setText("~~");
                            left2.setText("|");
                        }
                        if (situation == "waitright") {
                            right2.setText("|");
                            left2.setText("~~");
                        }
                    }
                }
        );
        b3 = new Berge("b3", bridge);
        b3.setshow(
                new Update() {
                    @Override
                    public void show(String situation) {
                        if (situation == "left") {
                            bridgemiddle.setText("[    ]");
                            left3.setText(b3.getName());
                        }
                        if (situation == "middle") {
                            left3.setText("~~");
                            right3.setText("~~");
                            bridgemiddle.setText(b3.getName());
                        }
                        if (situation == "right") {
                            bridgemiddle.setText("[    ]");
                            right3.setText(b3.getName());
                        }
                        if (situation == "waitleft") {
                            right3.setText("~~");
                            left3.setText("|");
                        }
                        if (situation == "waitright") {
                            right3.setText("|");
                            left3.setText("~~");
                        }
                    }
                }

        );
        v1 = new Vehicle("v1", bridge);
        v1.setshow(
                new Update() {
                    @Override
                    public void show(String situation) {
                        if (situation == "down") {
                            bridgemiddle.setText("[    ]");
                            down1.setText(v1.getName());
                        }
                        if (situation == "middle") {
                            down1.setText("||");
                            up1.setText("||");
                            bridgemiddle.setText("[ " + v1.getName() + " ]");
                        }
                        if (situation == "up") {
                            bridgemiddle.setText("[    ]");
                            up1.setText(v1.getName());
                        }
                        if (situation == "waitup") {
                            up1.setText("|");
                            down1.setText("||");
                        }
                        if (situation == "waitdown") {
                            up1.setText("||");
                            down1.setText("|");
                        }

                    }
                }
        );
        v2 = new Vehicle("v2", bridge);
        v2.setshow(
                new Update() {
                    @Override
                    public void show(String situation) {
                        if (situation == "down") {
                            bridgemiddle.setText("[    ]");
                            down2.setText(v2.getName());
                        }
                        if (situation == "middle") {
                            down2.setText("||");
                            up2.setText("||");
                            bridgemiddle.setText("[ " + v2.getName() + " ]");
                        }
                        if (situation == "up") {
                            bridgemiddle.setText("[    ]");
                            up2.setText(v2.getName());
                        }
                        if (situation == "waitup") {
                            up2.setText("|");
                            down2.setText("||");
                        }
                        if (situation == "waitdown") {
                            up2.setText("||");
                            down2.setText("|");
                        }

                    }
                }
        );
        v3 = new Vehicle("v3", bridge);
        v3.setshow(
                new Update() {
                    @Override
                    public void show(String situation) {
                        if (situation == "down") {
                            bridgemiddle.setText("[    ]");
                            down3.setText(v3.getName());
                        }
                        if (situation == "middle") {
                            down3.setText("||");
                            up3.setText("||");
                            bridgemiddle.setText("[ " + v3.getName() + " ]");
                        }
                        if (situation == "up") {
                            bridgemiddle.setText("[    ]");
                            up3.setText(v3.getName());
                        }
                        if (situation == "waitup") {
                            up3.setText("|");
                            down3.setText("||");
                        }
                        if (situation == "waitdown") {
                            up3.setText("||");
                            down3.setText("|");
                        }

                    }
                }
        );


    }

    public void run_thread(int number) {
        v1.start();
        b1.start();
        v2.start();
        b2.start();
        v3.start();
        b3.start();
        switch (number) {
            case 1:
                v2.stop();
                b2.stop();
                v3.stop();
                b3.stop();
                break;
            case 2:
                v3.stop();
                b3.stop();
                break;
            case 3:
                break;
            default:

        }
    }

    public void close_threads() {
        b1.stop();
        b2.stop();
        b3.stop();
        v1.stop();
        v2.stop();
        v3.stop();
    }

}
