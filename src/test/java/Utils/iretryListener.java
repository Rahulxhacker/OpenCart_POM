package Utils;

import org.testng.IAnnotationTransformer;

public class iretryListener implements IAnnotationTransformer {
	public void transform(org.testng.annotations.ITestAnnotation annotation, Class testClass,
			java.lang.reflect.Constructor testConstructor, java.lang.reflect.Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
