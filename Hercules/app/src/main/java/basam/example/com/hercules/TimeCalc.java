package basam.example.com.hercules;

public class TimeCalc extends TimeAbs {

    public double[] calcTime(double[] p1, double[] p2, double wrist, int exp) {

        double[] time = new double[8];
        double[] speed;
        double speedX = 0;

        if (exp > 5){exp = 5;}
        if (exp < 1){exp = 1;}

        if (wrist < 16.5) {
            speed = new double[] {0.022, 0.011, 0.007, 0.004, 0.003};
            speedX = speed [exp-1];
        }

        else if ((wrist >= 16.5)&&(wrist <= 19.5)) {
            speed = new double[] {0.033, 0.017, 0.008, 0.005, 0.003};
            speedX = speed [exp-1];
        }

        else if (wrist > 19.5) {
            speed = new double[] {0.044, 0.022, 0.011, 0.006, 0.003};
            speedX = speed [exp-1];
        }

        for(int i = 0; i < 8; i++)
        {
            time[i] = (p1[i] - p2[i])/speedX;
        }
            return time;
    }

    public void SetData() {
        System.out.println("ok, работаю");
    }
}