/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author admin
 */
public class thongKe {
    double doanhThu,doanhThuTB;

    public thongKe(double doanhThu, double doanhThuTB) {
        this.doanhThu = doanhThu;
        this.doanhThuTB = doanhThuTB;
    }

    public thongKe() {
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public void setDoanhThuTB(double doanhThuTB) {
        this.doanhThuTB = doanhThuTB;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public double getDoanhThuTB() {
        return doanhThuTB;
    }
    
}
