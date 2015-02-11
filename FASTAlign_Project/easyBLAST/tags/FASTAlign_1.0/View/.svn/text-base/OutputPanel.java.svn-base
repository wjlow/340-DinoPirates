package View;

import Model.ModelInterface;
import Model.opencsv.CSVReader;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import Model.opencsv.CSVWriter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * This class displays the output data from BLAST.
 * Precondition: The output file generated in the FASTAlign
 *               and it is ':' delimited
 * Postcondition: A file with user-specified name is written in the
 * user-specified location. This file is readable by Notepad.
 * @author lawrence
 */
public class OutputPanel extends JPanel implements ActionListener
{
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private JTable table;
    private JFileChooser fileChooser = new JFileChooser();

    private ModelInterface model;
    
    private final String[] columnHeaders =
        {"Query ID", "Database ID", "Identity %", "Alignment Length",
          "# mismatches", "# gap openings", "Query Start", "Query End",
          "Subject Start", "Subject End", "Expected Value", "HSP Bit Score"};

    private JButton saveOutput;


    public OutputPanel(ModelInterface model)
    {
        this.model = model;
        setLayout(new BorderLayout());
        table = new JTable(defaultTableModel)
        {
            @Override
            protected JTableHeader createDefaultTableHeader()
            {
                return new JTableHeader(columnModel)
                {
                    @Override
                    public String getToolTipText(MouseEvent e)
                    {
                        java.awt.Point p = e.getPoint();
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex =
                                columnModel.getColumn(index).getModelIndex();
                        return columnHeaders[realIndex];
                    }
                };
            }
        };
        defaultTableModel.setColumnIdentifiers(columnHeaders);

        JLabel header = new JLabel("Output");
        header.setFont(new Font("Sans Serif", Font.BOLD, 20));
        add(header, BorderLayout.NORTH);
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane, BorderLayout.CENTER);

        saveOutput = new JButton("Save Output");
        JPanel saveButtonPanel = new JPanel(new FlowLayout());
        saveButtonPanel.add(saveOutput);

        saveOutput.addActionListener(this);
        add(saveButtonPanel, BorderLayout.SOUTH);
        //saveOutput.setFont(new Font("Sans Serif", Font.PLAIN, 16));
    }

    /**
     * Accepts a File object and appends its contents to the JTable on-screen.
     * @param file A File object
     */
    public void addData(File file)
    {
        try
        {
            // Reads the File object.
            // \t is used as delimiter.
            CSVReader reader = new CSVReader(new FileReader(file), '\t');

            //try adding dummy data
            String[] line;

            while ((line = reader.readNext()) != null)
            {
                defaultTableModel.addRow(line);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        int i, j;
        CSVWriter writer;

        int returnValue = fileChooser.showSaveDialog(fileChooser);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            System.out.println(fileChooser.getSelectedFile());
            model.setOutputTableSaveLocation(fileChooser.getSelectedFile());
        }

        try
        {
            // we need to use the quote character here because of the spaces
            // in the header of the CSV file.
            writer = new CSVWriter(new FileWriter(
                    model.getOutputTableSaveLocation()), ',');
            writer.writeNext(columnHeaders);


            String[] line = new String[12];
            if (e.getSource() == saveOutput)
            {
                for (i = 0; i < table.getRowCount(); i++)
                {
                    for (j = 0; j < table.getColumnCount(); j++)
                    {
                        line[j] = table.getValueAt(i, j).toString();
                    }
                    writer.writeNext(line);
                }
                writer.close();
            }
        }
        catch (IOException ee)
        {

        }
    }
}
