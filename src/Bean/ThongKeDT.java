
package Bean;

import DAO.ThongKeDao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Tran_Hang
 */
public class ThongKeDT {
    ThongKeDao dao = new ThongKeDao();
    List<String> listNam = new ArrayList<>();
    static JComboBox cb;

    public void run() {
        JFrame frame = new JFrame();

        listNam = dao.getNamDT();

        String[] Lnam = new String[listNam.size()];
        listNam.toArray(Lnam);
        cb = new JComboBox(Lnam);

        cb.setBounds(550, 435, 100, 25);
        cb.setBackground(Color.gray);
        frame.add(cb);
        
        int nam = Integer.parseInt(cb.getSelectedItem().toString());
        double thang1 = ThongKeDao.getThongKeDT(nam, 1);
        double thang2 = ThongKeDao.getThongKeDT(nam, 2);
        double thang3 = ThongKeDao.getThongKeDT(nam, 3);
        double thang4 = ThongKeDao.getThongKeDT(nam, 4);
        double thang5 = ThongKeDao.getThongKeDT(nam, 5);
        double thang6 = ThongKeDao.getThongKeDT(nam, 6);
        double thang7 = ThongKeDao.getThongKeDT(nam, 7);
        double thang8 = ThongKeDao.getThongKeDT(nam, 8);
        double thang9 = ThongKeDao.getThongKeDT(nam, 9);
        double thang10 = ThongKeDao.getThongKeDT(nam, 10);
        double thang11 = ThongKeDao.getThongKeDT(nam, 11);
        double thang12 = ThongKeDao.getThongKeDT(nam, 12);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(thang1, "Đồng", "Tháng 1");
        dataset.addValue(thang2, "Đồng", "Tháng 2");
        dataset.addValue(thang3, "Đồng", "Tháng 3");
        dataset.addValue(thang4, "Đồng", "Tháng 4");
        dataset.addValue(thang5, "Đồng", "Tháng 5");
        dataset.addValue(thang6, "Đồng", "Tháng 6");
        dataset.addValue(thang7, "Đồng", "Tháng 7");
        dataset.addValue(thang8, "Đồng", "Tháng 8");
        dataset.addValue(thang9, "Đồng", "Tháng 9");
        dataset.addValue(thang10,"Đồng", "Tháng 10");
        dataset.addValue(thang11, "Đồng", "Tháng 11");
        dataset.addValue(thang12, "Đồng", "Tháng 12");

        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU CÁC THÁNG",
                "Năm", "Đồng",
                dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);

        frame.add(chartPanel);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nam = Integer.parseInt(cb.getSelectedItem().toString());
                double thang1 = ThongKeDao.getThongKeDT(nam, 1);
        double thang2 = ThongKeDao.getThongKeDT(nam, 2);
        double thang3 = ThongKeDao.getThongKeDT(nam, 3);
        double thang4 = ThongKeDao.getThongKeDT(nam, 4);
        double thang5 = ThongKeDao.getThongKeDT(nam, 5);
        double thang6 = ThongKeDao.getThongKeDT(nam, 6);
        double thang7 = ThongKeDao.getThongKeDT(nam, 7);
        double thang8 = ThongKeDao.getThongKeDT(nam, 8);
        double thang9 = ThongKeDao.getThongKeDT(nam, 9);
        double thang10 = ThongKeDao.getThongKeDT(nam, 10);
        double thang11 = ThongKeDao.getThongKeDT(nam, 11);
        double thang12 = ThongKeDao.getThongKeDT(nam, 12);
                
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                dataset.addValue(thang1, "Đồng", "Tháng 1");
        dataset.addValue(thang2, "Đồng", "Tháng 2");
        dataset.addValue(thang3, "Đồng", "Tháng 3");
        dataset.addValue(thang4, "Đồng", "Tháng 4");
        dataset.addValue(thang5, "Đồng", "Tháng 5");
        dataset.addValue(thang6, "Đồng", "Tháng 6");
        dataset.addValue(thang7, "Đồng", "Tháng 7");
        dataset.addValue(thang8, "Đồng", "Tháng 8");
        dataset.addValue(thang9, "Đồng", "Tháng 9");
        dataset.addValue(thang10,"Đồng", "Tháng 10");
        dataset.addValue(thang11, "Đồng", "Tháng 11");
        dataset.addValue(thang12, "Đồng", "Tháng 12");
                JFreeChart barChart = ChartFactory.createBarChart(
                        "BIỂU ĐỒ DOANH THU CÁC THÁNG",
                        "Năm", "Đồng",
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
