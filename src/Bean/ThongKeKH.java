package Bean;

import DAO.ThongKeDao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Tran_Hang
 */
public class ThongKeKH {

    ThongKeDao dao = new ThongKeDao();
    List<String> listNam = new ArrayList<>();
    static JComboBox cb;

    public void run() {
        JFrame frame = new JFrame();

        listNam = dao.getNam();

        String[] Lnam = new String[listNam.size()];
        listNam.toArray(Lnam);
        cb = new JComboBox(Lnam);

        cb.setBounds(550, 435, 100, 25);
        cb.setBackground(Color.gray);
        frame.add(cb);
        
        int nam = Integer.parseInt(cb.getSelectedItem().toString());
        double thang1 = ThongKeDao.getThongKeKH(nam, 1);
        double thang2 = ThongKeDao.getThongKeKH(nam, 2);
        double thang3 = ThongKeDao.getThongKeKH(nam, 3);
        double thang4 = ThongKeDao.getThongKeKH(nam, 4);
        double thang5 = ThongKeDao.getThongKeKH(nam, 5);
        double thang6 = ThongKeDao.getThongKeKH(nam, 6);
        double thang7 = ThongKeDao.getThongKeKH(nam, 7);
        double thang8 = ThongKeDao.getThongKeKH(nam, 8);
        double thang9 = ThongKeDao.getThongKeKH(nam, 9);
        double thang10 = ThongKeDao.getThongKeKH(nam, 10);
        double thang11 = ThongKeDao.getThongKeKH(nam, 11);
        double thang12 = ThongKeDao.getThongKeKH(nam, 12);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(thang1, "Số người", "Tháng 1");
        dataset.addValue(thang2, "Số người", "Tháng 2");
        dataset.addValue(thang3, "Số người", "Tháng 3");
        dataset.addValue(thang4, "Số người", "Tháng 4");
        dataset.addValue(thang5, "Số người", "Tháng 5");
        dataset.addValue(thang6, "Số người", "Tháng 6");
        dataset.addValue(thang7, "Số người", "Tháng 7");
        dataset.addValue(thang8, "Số người", "Tháng 8");
        dataset.addValue(thang9, "Số người", "Tháng 9");
        dataset.addValue(thang10, "Số người", "Tháng 10");
        dataset.addValue(thang11, "Số người", "Tháng 11");
        dataset.addValue(thang12, "Số người", "Tháng 12");

        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ SỐ LƯỢNG KHÁCH THUÊ",
                "Năm", "Số người",
                dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);

        frame.add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nam = Integer.parseInt(cb.getSelectedItem().toString());
                double thang1 = ThongKeDao.getThongKeKH(nam, 1);
                double thang2 = ThongKeDao.getThongKeKH(nam, 2);
                double thang3 = ThongKeDao.getThongKeKH(nam, 3);
                double thang4 = ThongKeDao.getThongKeKH(nam, 4);
                double thang5 = ThongKeDao.getThongKeKH(nam, 5);
                double thang6 = ThongKeDao.getThongKeKH(nam, 6);
                double thang7 = ThongKeDao.getThongKeKH(nam, 7);
                double thang8 = ThongKeDao.getThongKeKH(nam, 8);
                double thang9 = ThongKeDao.getThongKeKH(nam, 9);
                double thang10 = ThongKeDao.getThongKeKH(nam, 10);
                double thang11 = ThongKeDao.getThongKeKH(nam, 11);
                double thang12 = ThongKeDao.getThongKeKH(nam, 12);
                
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                dataset.addValue(thang1, "Số người", "Tháng 1");
                dataset.addValue(thang2, "Số người", "Tháng 2");
                dataset.addValue(thang3, "Số người", "Tháng 3");
                dataset.addValue(thang4, "Số người", "Tháng 4");
                dataset.addValue(thang5, "Số người", "Tháng 5");
                dataset.addValue(thang6, "Số người", "Tháng 6");
                dataset.addValue(thang7, "Số người", "Tháng 7");
                dataset.addValue(thang8, "Số người", "Tháng 8");
                dataset.addValue(thang9, "Số người", "Tháng 9");
                dataset.addValue(thang10, "Số người", "Tháng 10");
                dataset.addValue(thang11, "Số người", "Tháng 11");
                dataset.addValue(thang12, "Số người", "Tháng 12");

                JFreeChart barChart = ChartFactory.createBarChart(
                        "BIỂU ĐỒ SỐ LƯỢNG NGƯỜI THUÊ",
                        "Năm", "Số người",
                        dataset, PlotOrientation.VERTICAL, false, false, false);

                ChartPanel chartPanel = new ChartPanel(barChart);

                frame.add(chartPanel);
                frame.setSize(1000, 500);
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);

            }
        });

    }
}
