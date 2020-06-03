package basam.example.com.hercules;

import java.util.ArrayList;

public interface BDHelper {
    ArrayList<Integer> GetData();
    double[] GetData(double k);
    void SetData(ArrayList<Integer> data);
}