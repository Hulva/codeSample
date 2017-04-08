package hulva.luva.learn.kafka.commandline;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import hulva.luva.learn.kafka.log.Logging;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月7日
 * @description
 *
 */
public class CommandLineUtils extends Logging {

  public static void checkRequiredArgs(OptionParser parser, OptionSet options, OptionSpec<?>[] required) throws IOException {
    for (OptionSpec<?> arg : required) {
      if (!options.has(arg)) {
        printUsageAndExist(parser, "Missing required argument \"" + arg + "\"");
      }
    }
  }

  public static void printUsageAndExist(OptionParser parser, String message) throws IOException {
    System.err.println(message);
    parser.printHelpOn(System.err);
    System.exit(1);
  }

  public static Properties parseKeyValueArgs(Iterable<String> args, boolean acceptMissingValue) {
    Iterator<String> argsIt = args.iterator();
    Properties props = new Properties();
    argsIt.forEachRemaining(c -> {
      String[] arrArg = c.split("=");
      if (!(arrArg.length == 0)) {
        if (arrArg.length == 1 && acceptMissingValue) {
          props.put(arrArg[0], "");
        } else {
          throw new IllegalArgumentException("Missing value for key +" + arrArg[0]);
        }
        if (arrArg.length == 2) {
          props.put(arrArg[0], arrArg[1]);
        } else {
          System.err.println("Invalid command line properties: " + c);
          System.exit(1);
        }
      }

    });
    return props;
  }
}
