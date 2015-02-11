package Controller;

import Model.ModelInterface;
import View.Input.InputEnd;
import View.Input.InputGroupingPanel;
import View.View;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This ChangeListener listens for the user changing the top tabs on Input tab.
 * @author lawrence
 */
public class InputTabChangeListener implements ChangeListener
{
    private ModelInterface model;
    private View view;
    private int wantToGroup;

    /**
     *
     * @param m The ModelInterface that the ChangeListener has access to.
     */
    public InputTabChangeListener(final ModelInterface m)
    {
        this.model = m;
    }

    /**
     *
     * @param m The ModelInterface that the ChangeListener has access to.
     * @param v The View that the ChangeListener has access to.
     */
    public InputTabChangeListener(final ModelInterface m, final View v)
    {
        this.model = m;
        this.view = v;
    }

    public void stateChanged(ChangeEvent event)
    {
        String currentTab = ((JTabbedPane) event.getSource()).getTitleAt(
                ((JTabbedPane) event.getSource()).getSelectedIndex());
        JTabbedPane currentPane = view.getInputPanel().getInputTabMenu();
        JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();

        if (currentTab.equals("ID Selection"))
        {
            if (!model.isBLASTMode())
            {
                wantToGroup = JOptionPane.showConfirmDialog(
                        null, "Do you want to split the "
                        + "query file(s) into groups?", "FASTAlign",
                        JOptionPane.YES_NO_OPTION);

                if (wantToGroup == JOptionPane.NO_OPTION)
                {
                    inputTabMenu.setSelectedIndex(
                            inputTabMenu.getSelectedIndex() + 2);
                    inputTabMenu.setEnabledAt(3, false);
                }
                else
                {
                    inputTabMenu.setEnabledAt(3, true);
                }
            }
        }

        if (currentTab.equals("Summary"))
        {
            if (wantToGroup == JOptionPane.YES_OPTION)
            {
                /* Start grouping code */
                int hasDisplayedError = 0;
                // Store the groups in ModelInterface
                InputGroupingPanel groupingPanel = view.getInputPanel()
                        .getInputGroupingPanel();
                int numberOfGroups = groupingPanel.getNoVisibleFields();
                List<List<String>> groups = new ArrayList<List<String>>();

                for (int i = 0; i < numberOfGroups; i++)
                {
                    String ids = groupingPanel.getGroupTextField()[i].getText();
                    String[] idList = ids.split(",");

                    List<String> tempIds = new ArrayList<String>();

                    for (int j = 0; j < idList.length; j++)
                    {
                        idList[j] = idList[j].trim();
                        if (!Pattern.matches("^[AaCcTtGg*]+", idList[j])
                                && hasDisplayedError == 0)
                        {
                            hasDisplayedError = 1;
                            if ((currentPane.getSelectedIndex()) == inputTabMenu
                                    .getSelectedIndex())
                            {
                                inputTabMenu.setSelectedIndex(inputTabMenu.
                                    getSelectedIndex() - 1);
                                    JOptionPane.showMessageDialog(
                                            null, "Inappropriate group "
                                            + "strings have been entered. "
                                            + "Groups strings containing "
                                            + "letters A, C, T, G are accepted."
                                            , "FASTAlign",
                                            JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else if (idList[j].length() != model.getLengthOfIDRegion()
                                && hasDisplayedError == 0)
                        {
                            hasDisplayedError = 1;
                            if ((currentPane.getSelectedIndex()) == inputTabMenu
                                    .getSelectedIndex())
                            {
                                inputTabMenu.setSelectedIndex(inputTabMenu.
                                    getSelectedIndex() - 1);
                                    JOptionPane.showMessageDialog(null,
                                         "Inappropriate group strings "
                                         + "have been entered. Groups string "
                                         + "must be of the same length as the "
                                         + "ID region selected",
                                         "FASTAlign",
                                         JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            tempIds.add(idList[j].toUpperCase());
                        }
                    }
                    groups.add(tempIds);
                }
                model.callMakeGroups(groups);

                if (model.checkForConflictsInGroups() && hasDisplayedError == 0)
                {
                    inputTabMenu.setSelectedIndex(inputTabMenu.
                                    getSelectedIndex() - 1);
                    JOptionPane.showMessageDialog(null,
                            "Conflict Found!",
                            "FASTAlign", JOptionPane.ERROR_MESSAGE);
                }

                /* End grouping code */
            }

            // Put Summary on page
            InputEnd inputEnd = view.getInputPanel().getInputEnd();
            inputEnd.getSummaryField().setText(inputEnd.getSummaryLines());
            InputFileParser ifp = new InputFileParser(model);
            ifp.actionPerformed(null);
        }
    }
}

