
public class Zemi {

    int m = 100;
    float cp = 4.2f;

    float twsi = 30;
    float tcsi = 4;

    int ua = 5000;

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
    double h1;
    double h4;

    // 凝縮器
    double h2;
    double h3;

    public void calculateWwso(double l_wso, double h_wso, double target) {

        double qws_high = m * cp *  (twsi - h_wso);
        double qws_low = m * cp *  (twsi - l_wso);

        double mid = (l_wso + h_wso) / 2;
        double qws_mid = m * cp * (twsi - mid);

        tlme_high = qws_high / ua;
        tlme_low = qws_low / ua;
        tlme_mid = qws_mid / ua;

        te_high = (h_wso * Math.exp((twsi - h_wso)/tlme_high) - twsi) / (Math.exp((twsi - h_wso)/tlme_high) - 1);
        te_low= (l_wso * Math.exp((twsi - l_wso)/tlme_low) - twsi) / (Math.exp((twsi - l_wso)/tlme_low) - 1);
        te_mid = (mid * Math.exp((twsi - mid)/tlme_mid) - twsi) / (Math.exp((twsi - mid)/tlme_mid) - 1);

        System.out.println(te_high);
        System.out.println(te_low);
        System.out.println(te_mid);

        // TODO () 内を、Qinで割ってあげる
        fx_wso_high = 1 - (qws_high);
        fx_wso_low = 1 - (qws_low);
        fx_wso_mid = 1 - (qws_mid);

        if (fx_wso_low > target || fx_wso_high < target) {
            System.out.println("can't be calculated");
        } else if (fx_wso_mid <= target) {
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
