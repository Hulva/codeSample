package hulva.luva.learn.kafka.commandline;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import hulva.luva.learn.kafka.common.Utils;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月7日
 * @description
 *
 */
public class GetPropertiesFromArgs {

  /**
   * @param args
   * @throws IOException
   */
  @SuppressWarnings("static-access")
  public static void main(String[] args) throws IOException {
    OptionParser parser = new OptionParser();
    ArgumentAcceptingOptionSpec<String> overrideOpt =
        parser.accepts("override", "Optional property that should override values set in server.properties file").withRequiredArg()
            .ofType(String.class);

    if (args.length == 0) {
      CommandLineUtils.printUsageAndExist(parser,
          "USAGE: java [options] %s server.properties [--override property=value]*".format(GetPropertiesFromArgs.class.getSimpleName()));
    }

    Properties props = Utils.loadProps(args[0]);

    if (args.length > 1) {
      OptionSet options = parser.parse(Arrays.copyOfRange(args, 1, args.length));

      if (options.nonOptionArguments().size() > 0) {
        CommandLineUtils.printUsageAndExist(parser,
            "Found non argument parameters: " + options.nonOptionArguments().toArray());
      }
      props.putAll(CommandLineUtils.parseKeyValueArgs(options.valuesOf(overrideOpt), true));
    }

    props.forEach((k, v) -> {
      System.out.println("key: " + k + " - value: " + v);
    });
  }


}
