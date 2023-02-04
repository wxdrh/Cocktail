package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;


public class ExtentReportFactory extends UtilitiesFactory {

    String fileName = reportLocation + "extentreport.html";


    public ExtentReportFactory(){
    }


    public void ExtentReport() {
        //First is to create Extent Reports
        extent = new ExtentReports();

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("demo Automation Test Report");
        htmlReporter.config().setEncoding("uft-8");
        htmlReporter.config().setReportName("Automation Execution Report");
        htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        extent.attachReporter(htmlReporter);


    }

    public void ExtentFailStep(String exceptionMsg) throws IOException {
        testLog.log(Status.FAIL,
                "<details>" + "<summary> <b> <font color=red> Cause of Failure: </b> " + exceptionMsg
                        + "</font>" + "</summary>");
    }

    public void ExtentPassStep(String testName) throws IOException {
        testLog.log(Status.PASS,
                "<summary> <b> <font color=green> "+ testName + " Test Passed </b> "
                        + "</font>" + "</summary>");
    }


    public void FlushReport(){
        extent.flush();
    }
}
