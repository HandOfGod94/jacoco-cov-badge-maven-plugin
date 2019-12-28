package io.github.handofgod94;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.codehaus.plexus.component.configurator.ComponentConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;

import java.io.File;

public class MyMojoTest extends AbstractMojoTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  public void testBadgeGoal_WhenPomIsValid_ItGeneratesBadge() throws Exception {
    File testPom = new File(getBasedir(), "src/test/resources/basic-plugin-test-pom.xml");
    File outputFile = new File(getBasedir(), "target/test-classes/out.svg");
    File expectedSvg = new File(getBasedir(), "src/test/resources/in.svg");

    MyMojo mojo = (MyMojo) lookupMojo("badge", testPom);

    Assertions.assertNotNull(mojo);

    mojo.execute();

    // Verify svg file generated
    Assertions.assertTrue(outputFile.exists());

    Diff diff =
      DiffBuilder
        .compare(Input.fromFile(outputFile))
        .withTest(expectedSvg)
        .ignoreWhitespace().ignoreComments().build();

    Assertions.assertFalse(diff.hasDifferences(), diff.toString());
  }

  public void testBadgeGoal_WhenPomIsInvalid_ItThrowsException() throws Exception {
    File testPom = new File(getBasedir(), "src/test/resources/basic-plugin-test-invalid-pom.xml");

    Assertions.assertThrows(ComponentConfigurationException.class, () -> {
      MyMojo mojo = (MyMojo) lookupMojo("badge", testPom);
      mojo.execute();
    });
  }
}
