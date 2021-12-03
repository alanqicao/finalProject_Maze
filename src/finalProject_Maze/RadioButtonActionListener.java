/**
 * 
 */
package finalProject_Maze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

/**
 * @author Qi Cao
 *
 */
class RadioButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
    	
        JRadioButton button = (JRadioButton) event.getSource();
 
        if (button == rdbtnNewRadioButton) {
 
            // option Linux is selected
 
        } else if (button == rdbtnNewRadioButton_1) {
 
            // option Windows is selected
 
        }
    }
}