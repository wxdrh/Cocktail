package listeners;

import utilities.ExtentReportFactory;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static utilities.UtilitiesFactory.testLog;

public class ExtentListener implements ITestListener {

    ExtentReportFactory extentReport = new ExtentReportFactory();

    public ExtentListener() {
        extentReport.ExtentReport();
    }



    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String testName;
        try{
            testName = iTestResult.getName();
            extentReport.ExtentPassStep(testName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try{
            String exceptionMessage = iTestResult.getThrowable().getMessage();
            extentReport.ExtentFailStep(exceptionMessage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onTestStart(ITestResult iTestContext) {
        testLog = extentReport.extent.createTest(iTestContext.getName());
        testLog.log(Status.INFO,"Test Execution Started");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReport.FlushReport();
    }



}
