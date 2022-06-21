
public class Zemi {

    int m = 100;
    float cp = 4.2f;

    float twsi = 30;
    float tcsi = 4;

    int ua = 5000;

    float mwf = 2.0f;

    float tlme_high;
    float tlme_low;
    float tlme_mid;
    float tlmc_high;
    float tlmc_low;
    float tlmc_mid;

    double te_high;
    double te_low;
    double te_mid;

    double tc_high;
    double tc_low;
    double tc_mid;

    public float calculateWwso(float[] num1, float[] num2, int target) {

        int len1 = num1.length - 1;

        for (int i = 0; i < num1.length; i++){

            float qws_high = m * cp *  (twsi - num1[len1]);
            float qws_low = m * cp *  (twsi - num1[i]);

            float mid = (num1[i] + num1[len1]) / 2;
            float qws_mid = m * cp * (twsi - mid);

            tlme_high = qws_high / ua;
            tlme_low = qws_low / ua;
            tlme_mid = qws_mid / ua;

            te_high = (num1[len1] * Math.exp((twsi-num1[len1])/tlme_high) - twsi) / (Math.exp((twsi-num1[len1])/tlme_high) - 1);
            te_low= (num1[len1] * Math.exp((twsi-num1[i])/tlme_low) - twsi) / (Math.exp((twsi-num1[len1])/tlme_low) - 1);
            te_mid = (mid * Math.exp((twsi-mid)/tlme_mid) - twsi) / (Math.exp((twsi-mid)/tlme_mid) - 1);
        }

        int len2 = num2.length - 1;

        for (int i = 0; i < num2.length; i++){

            float qcs_high = m * cp * (num2[len2] - tcsi);
            float qcs_low = m * cp * (num2[0] - tcsi);

            float mid = (num2[i] + num2[len2]) / 2;
            float qcs_mid = m * cp * (mid - tcsi);

            tlmc_high = qcs_high / ua;
            tlmc_low = qcs_low / ua;
            tlmc_mid = qcs_mid / ua;

            tc_high = (num2[len2] * Math.exp((num2[len2] - tcsi) / tlmc_high) - tcsi) / (Math.exp((num2[len2] - tcsi) / tlmc_high) - 1);
            tc_low = (num2[i] * Math.exp((num2[i] - tcsi) / tlmc_low) - tcsi) / (Math.exp((num2[i] - tcsi) / tlmc_low) - 1);
            tc_mid = (mid * Math.exp((mid - tcsi) / tlmc_mid) - tcsi) / (Math.exp((mid - tcsi) / tlmc_mid) - 1);
        }

        return 0f;
    }
}
