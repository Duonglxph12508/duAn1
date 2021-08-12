package Bean;

import DAO.ThongKeDao;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Tran_Hang
 */
public class ThongKePT {

    ThongKeDao dao = new ThongKeDao();

    public static void run() {
        double phongTrong = ThongKeDao.getThongKePT("Còn trống");
        double phongThue = ThongKeDao.getThongKePT("Đã thuê");
        double phongSua = ThongKeDao.getThongKePT("Đang sửa");

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Phòng trống", new Double(phongTrong));
        dataset.setValue("Phòng đang sửa chữa", new Double(phongSua));
        dataset.setValue("Phòng đang cho thuê", new Double(phongThue));

        JFreeChart chart = ChartFactory.createPieChart(
                "TỔNG HỢP TÌNH TRẠNG CÁC PHÒNG TRỌ", dataset, true, true, true);

        JFreeChart pieChart = chart;
        ChartPanel chartPanel = new ChartPanel(pieChart);
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
