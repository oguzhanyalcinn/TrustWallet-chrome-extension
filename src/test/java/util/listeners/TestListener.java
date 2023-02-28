package util.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.BaseTest;

import java.util.Arrays;

public class TestListener extends BaseTest implements ITestListener {

    private static ExtentReports extent = new ExtentReports();
    private ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReport.html");

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String passedTest = iTestResult.getName();

        reporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();

        extent.attachReporter(reporter);
        extent.createTest(passedTest)
                .assignCategory(iTestResult.getMethod().getGroups())
                .log(Status.PASS, iTestResult.getThrowable())
                .info(iTestResult.getMethod().getDescription())
                .info(Arrays.toString(iTestResult.getMethod().getGroups()));
        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String failedTest = iTestResult.getName();

        reporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();

        extent.attachReporter(reporter);
        extent.createTest(failedTest)
                .assignCategory(iTestResult.getMethod().getGroups())
                .log(Status.FAIL, iTestResult.getThrowable())
                .info(iTestResult.getMethod().getDescription())
                .info(Arrays.toString(iTestResult.getMethod().getGroups()));
        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String skippedTest = iTestResult.getName();

        reporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();

        extent.attachReporter(reporter);
        extent.createTest(skippedTest)
                .assignCategory(iTestResult.getMethod().getGroups())
                .log(Status.SKIP, iTestResult.getThrowable())
                .info(iTestResult.getMethod().getDescription())
                .info(Arrays.toString(iTestResult.getMethod().getGroups()));
        extent.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
}