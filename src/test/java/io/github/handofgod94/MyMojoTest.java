package io.github.handofgod94;

import java.io.File;
import junit.framework.Assert;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;

public class MyMojoTest extends AbstractMojoTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  public void testBadgeGoal() throws Exception {
    File testPom = new File(getBasedir(), "src/test/resources/basic-plugin-test-pom.xml");
    File outputFile = new File(getBasedir(), "target/test-classes/out.svg");
    File expectedSvg = new File(getBasedir(), "src/test/resources/in.svg");

    MyMojo mojo = (MyMojo) lookupMojo("badge", testPom);

    Assert.assertNotNull(mojo);

    mojo.execute();

    // Verify svg file generated
    Assert.assertTrue(outputFile.exists());
    Diff diff =
      DiffBuilder
        .compare(Input.fromFile(outputFile))
        .withTest(expectedSvg)
        .ignoreWhitespace().ignoreComments().build();

    Assert.assertFalse(diff.toString(), diff.hasDifferences());
  }
}
