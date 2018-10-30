package dotsandboxestry;

import javax.swing.JLabel;

public class CounterLabel extends JLabel {
	// version number of this class, used for serialization
	private static final long serialVersionUID = 1L;
	// the value of the counter
	private int count;
	// the description of the counter
	private String description;
	private void update() {
		setText(description + Integer.toString(this.count));
	}
	public CounterLabel(String desc) {
		// allow the super class to initialize (JLabel)
		super();
		// save the description
		this.description = desc;
		// start the counter at zero
		reset();
	}
        
        public int getPoint()
        {
            return this.count;
        }
	/**
	 * Increments the counter and updates the text label
	 */
	public void increment() {
		this.count++;
		update();
	}

	/**
	 * Adds the value to the counter and updates the label
	 * 
	 * @param n the number to add to the counter
	 */
	public void add(int n) {
		this.count += n;
		update();
	}
        public void setPosition(String str)
        {
            this.description =str;
            update();
        }
	/**
	 * Resets the counter to zero and updates the text label
	 */
	public void reset() {
		this.count = 0;
		update();
	}

    void decrease() {
        this.count--;
        update();
    }
}