package View;

import Model.ModelInterface;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import Model.opencsv.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 * This class displays the output data from BLAST.
 * Precondition: There is a "out.txt" file in C:\\ and it is ':' delimited.
 * Postcondition: A file with user-specified name is written in the
 * user-specified location. This file is readable by Notepad.
 * @author lawrence
 */
public class OutputPanel extends JPanel implements ActionListener {
    private DefaultTableModel   defaultTableModel = new DefaultTableModel();
    private JTable table = new JTable(defaultTableModel);
    private JFileChooser fileChooser = new JFileChooser();

    private ModelInterface model;
    /**
     * Default path for BLAST's output
     */
    private String filePath = "u:\\BLAST_OUT.txt";

    final String[] columnHeaders = 
        { "Query ID", "Database ID", "Identity %", "Alignment Length",
          "# mismatches", "# gap openings", "Query Start", "Query End",
          "Subject Start", "Subject End", "Expected Value", "HSP Bit Score" };

    private JButton saveOutput;

    /**
     * 
     * @param m Model
     */
    public OutputPanel(ModelInterface model)
    {
        this.model = model;
        setLayout(new FlowLayout());
        defaultTableModel.setColumnIdentifiers(columnHeaders);

        File file = new File(filePath);

        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane);
        addData(file);
        saveOutput = new JButton("Save Output");
        saveOutput.addActionListener(this);
        add(saveOutput);
    }

    /**
     * Accepts a File object and appends its contents to the JTable on-screen.
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

            while((line = reader.readNext()) != null)
            {
                defaultTableModel.addRow(line);
            }
        }
        catch (IOException e)
        {

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
                for(i = 0; i< table.getRowCount(); i++)
                {
                    for(j=0; j< table.getColumnCount(); j++)
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

    void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    String getFilePath()
    {
        return filePath;
    }
}