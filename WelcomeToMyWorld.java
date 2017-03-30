import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class WelcomeToMyWorld extends Applet implements ActionListener, Runnable {

	int xCoord;
	Thread thread;
	Button buttonStart, buttonPause, buttonResume, buttonStop;
	TextField textFieldSpeed;
	Label labelSpeed;
	Image background, car;

	public void init() {
		labelSpeed = new Label("SPEED");
		labelSpeed.setForeground(Color.orange);
		add(labelSpeed);

		textFieldSpeed = new TextField(5);
		add(textFieldSpeed);

		buttonStart = new Button("START");
		buttonStart.addActionListener(this);
		add(buttonStart);

		buttonResume = new Button("RESUME");
		buttonResume.addActionListener(this);
		add(buttonResume);

		buttonPause = new Button("PAUSE");
		buttonPause.addActionListener(this);
		add(buttonPause);

		buttonStop = new Button("STOP");
		buttonStop.addActionListener(this);
		add(buttonStop);

		background = getImage(getCodeBase(), "background.png");
		car = getImage(getCodeBase(), "car.png");
	}

	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == buttonStart && thread == null) {
			thread = new Thread(this);
			thread.start();
		} else if (actionEvent.getSource() == buttonResume && thread != null) {
			thread.resume();
		} else if (actionEvent.getSource() == buttonPause && thread != null) {
			thread.suspend();
		} else if (actionEvent.getSource() == buttonStop && thread != null) {
			thread.stop();
			thread = null;
		}
	}

	public void run() {
		for (xCoord = 0; xCoord < 840; xCoord += 5) {
			try {
				repaint();
				Thread.sleep(Integer.parseInt(textFieldSpeed.getText()));
			} catch (Exception e) { }
		}
	}

	public void paint(Graphics graphics) {
		graphics.drawImage(background, 0, 0, this);

		if (xCoord > 600) {
			graphics.drawImage(car, 600, 300, this);
		} else {
			graphics.drawImage(car, 10 + xCoord, 300, this);
		}

		if (xCoord > 600 && xCoord < 850) {
			graphics.setColor(Color.orange);
			graphics.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,20));
			graphics.drawString("WELCOME", 570, 70);
			graphics.drawString("TO MY WORLD :-)", 570, 90);
		}
	}
}
/*<applet code="WelcomeToMyWorld" width=853 height=480>
</applet>*/