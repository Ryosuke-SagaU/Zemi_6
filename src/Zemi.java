
public class Zemi {

    int m = 100;
    float cp = 4200f;

    float twsi = 30f;
    float tcsi = 4f;

    int ua = 5000000;

    float mwf = 2.0f;

    double tlme_high;
    double tlme_low;
    double tlme_mid;
    double tlmc_high;
    double tlmc_low;
    double tlmc_mid;

    double te_high;
    double te_low;
    double te_mid;

    double tc_high;
    double tc_low;
    double tc_mid;

    double fx_wso_high;
    double fx_wso_low;
    double fx_wso_mid;

    double fx_cso_high;
    double fx_cso_low;
    double fx_cso_mid;

    double qin_high;
    double qin_low;
    double qin_mid;

    double qout_high;
    double qout_low;
    double qout_mid;

    // 蒸発器
    double h1_high = 512521.9;
    double h4_high = -692378.8;

    double h1_low = 510818.8;
    double h4_low = -692452.8;

    double h1_mid = 511679.6;
    double h4_mid = -692416.2;

    // 凝縮器
    double h2_high = 449011.5;
    double h3_high = -732315.6;

    double h2_low = 454827.4;
    double h3_low = -732315.6;

    double h2_mid = 451868.7;
    double h3_mid = -732315.6;

    public void calculateWwso(double l_wso, double h_wso, double target) {

        double qws_high = m * cp *  (twsi - h_wso);
        double qws_low = m * cp *  (twsi - l_wso);

        double mid = (l_wso + h_wso) / 2;
        double qws_mid = m * cp * (twsi - mid);

        System.out.println(qws_high);
        System.out.println(qws_low);
        System.out.println(qws_mid);

        tlme_high = qws_high / ua;
        tlme_low = qws_low / ua;
        tlme_mid = qws_mid / ua;

        te_high = (h_wso * Math.exp((twsi - h_wso)/tlme_high) - twsi) / (Math.exp((twsi - h_wso)/tlme_high) - 1);
        te_low = (l_wso * Math.exp((twsi - l_wso)/tlme_low) - twsi) / (Math.exp((twsi - l_wso)/tlme_low) - 1);
        te_mid = (mid * Math.exp((twsi - mid)/tlme_mid) - twsi) / (Math.exp((twsi - mid)/tlme_mid) - 1);

        System.out.println(te_high);
        System.out.println(te_low);
        System.out.println(te_mid);

        qin_high = mwf * (h1_high - h4_high);
        qin_low = mwf * (h1_low - h4_low);
        qin_mid = mwf * (h1_mid - h4_mid);

        // TODO () 内を、Qinで割ってあげる
        fx_wso_high = 1 - (qws_high / qin_high);
        fx_wso_low = 1 - (qws_low / qin_low);
        fx_wso_mid = 1 - (qws_mid / qin_mid);

        System.out.println(Math.abs(fx_wso_high));
        System.out.println(Math.abs(fx_wso_low));
        System.out.println(Math.abs(fx_wso_mid));

        if (fx_wso_low > target || fx_wso_high < target) {
            System.out.println("can't be calculated");
        } else if (Math.abs(fx_wso_mid) <= target) {
            System.out.println("good calculation");
        }
    }

    public void calculateWcso(double l_cso, double h_cso, double target) {

        double qcs_high = m * cp * (h_cso - tcsi);
        double qcs_low = m * cp * (l_cso - tcsi);

        double mid = (l_cso + h_cso) / 2;
        double qcs_mid = m * cp * (mid - tcsi);

        tlmc_high = qcs_high / ua;
        tlmc_low = qcs_low / ua;
        tlmc_mid = qcs_mid / ua;

        tc_high = (h_cso * Math.exp((h_cso - tcsi) / tlmc_high) - tcsi) / (Math.exp((h_cso - tcsi) / tlmc_high) - 1);
        tc_low = (l_cso * Math.exp((l_cso - tcsi) / tlmc_low) - tcsi) / (Math.exp((l_cso - tcsi) / tlmc_low) - 1);
        tc_mid = (mid * Math.exp((mid - tcsi) / tlmc_mid) - tcsi) / (Math.exp((mid - tcsi) / tlmc_mid) - 1);

        System.out.println(tc_high);
        System.out.println(tc_low);
        System.out.println(tc_mid);

        // TODO () 内を、Qoutで割ってあげる
        fx_cso_high = 1 - (qcs_high);
        fx_cso_low = 1 - (qcs_low);
        fx_cso_mid = 1 - (qcs_mid);

        if (fx_cso_low > target || fx_cso_high < target) {
            System.out.println("can't be calculated");
        } else if (fx_wso_mid <= target) {
            System.out.println("good calculation");
        }
    }
}
