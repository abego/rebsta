# Developer Notes

## Mutation Testing

Use the following command to start mutation testing:

    mvn org.pitest:pitest-maven:mutationCoverage
    
You will find the reports in `target/pit-reports`.

## Setup Rebsta Development Environment

Add the following line to your "`~/.bash_profile`" file:

    alias rebstad='java -jar /usr/local/bin/rebsta-cli-latest-complete.jar'

(Notice the "`d`" at the end of `rebstad`. This is to differentiate this 
"**d**evelopment" command from the official `rebsta` command.)

After running `mvn package` (or `mvn install`) you may now execute the 
following command to make the latest (development) build available to 
the command line:

    cp <some_path>/rebsta/rebsta-cli/target/rebsta-cli-<some_version>.jar /usr/local/bin/rebsta-cli-latest-complete.jar

Run `rebstad` to use the latest rebsta development build. 
    
## Update the project's 'Texts.java' files

Run the following commands from the project directory to update the project's
Static Text Access Class files (`Texts.java`), e.g. after adding new keys to the
`Texts.properties` resource bundle files:

- For core module: `rebsta -pp rebsta-core/src/main/resources/ rebsta-core/src/main/java/ org.abego.rebsta.core.internal.Texts`
- For CLI module: `rebsta -pp rebsta-cli/src/main/resources/ rebsta-cli/src/main/java/ org.abego.rebsta.cli.internal.Texts`

## Testing the generated code

`com.example.myapp.Texts.java` is a modified copy of 
`expectedResourceBundleSampleTextAccessClassJavaText_packagePrivate.txt`. When the
later changes (e.g. because class `StaticTextAccessClassTemplate` was modified) 
copy the new text to `Texts.java` and add the following method to `Texts.java`:

    // Manually added method after class generation to allow testing
    // the behaviour of "missing/deleted" keys.
    public static String missingKey() {
        return textFor("missingKey"); //NON-NLS
    }

