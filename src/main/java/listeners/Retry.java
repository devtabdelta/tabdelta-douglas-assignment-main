package listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private static final Logger Log = LogManager.getLogger(Retry.class.getName());
    private static final int  maxTry = 2;
    private int count  = 0;

    public String getResultStatusName (final int status) {
        String resultName = null;
        if (status == 1) {
            resultName = "SUCCESS";
        }
        if (status == 2) {
            resultName = "FAILURE";
        }
        if (status == 3) {
            resultName = "SKIP";
        }
        return resultName;
    }

    @Override
    public boolean retry (final ITestResult iTestResult) {
        if (!iTestResult.isSuccess ()) {
            if (this.count < maxTry) {
                Log.error("Retrying test {} with status {} for the {} time(s).", iTestResult.getName(), getResultStatusName(
                        iTestResult.getStatus()), this.count + 1);
                this.count++;
                return true;
            }
        }
        return false;
    }
}
