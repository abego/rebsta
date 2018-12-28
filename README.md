# Rebsta 
 
__ResourceBundle Static Text Access__

Use (generated) static methods to easily and reliably access 
locale-specific strings in a ResourceBundle.  

## Usage

In your Java project run Rebsta in the command line, e.g.:

    $ rebsta "src/main/resources" "src/main/java" com.example.myapp.Texts

This will read the ResourceBundle "`com.example.myapp.Texts`" located in the
resource directory "`src/main/resources`" (1) and create the text access 
Java class "`com.example.myapp.Texts`" in your Java sources root directory 
"`src/main/java`" (2).

(1) File "`Texts.java`" in the directory "`src/main/resources/com/example/myapp`"

(2) File `src/main/java/com/example/myapp/Texts.java`

## Example

Assume your ResourceBundle contains a Properties files (e.g. 
"`Texts.properties`") that looks like this:

    btnCancel=Cancel
    fileNotFound=File {0} not found.
    
Running Rebsta will generate this Java code:

    package com.example.myapp;
    
    import java.text.MessageFormat;
    import java.util.ResourceBundle;
    
    import static java.util.ResourceBundle.getBundle;
    
    public class Texts {
        private static final String BUNDLE_NAME = "com.example.myapp.Texts";
    
        private static final ResourceBundle BUNDLE = getBundle(BUNDLE_NAME);
    
        public static String btnCancel() {
            return textFor("btnCancel"); //NON-NLS
        }
    
        public static String fileNotFound(Object p0) {
            return textFor("fileNotFound", p0); //NON-NLS
        }
    
    
        private static String textFor(String key, Object... arguments) {
            return BUNDLE.containsKey(key)
                    ? new MessageFormat(BUNDLE.getString(key)).format(arguments)
                    : MessageFormat.format("??{0}??", key); //NON-NLS
        }
    }

## Synopsis

    usage: rebsta [options] resourcesRootDir sourceRootDir resourceBundleName
    
    Options:
    
    -c,--classname <arg>              The name of the texts access class (fully qualified)
                                      [Default: same as ResourceBundleName]
    -h,--help                         print this message
    -jc,--javafile-charset <arg>      The charset used for the generated Java file 
                                      [Default: UTF-8]
    -pp,--package-private             The generated Java class and its accessor methods are
                                      package-private [Default: public]
    -rc,--resourcebundle-charset <arg>The charset of the ResourceBundle file 
                                      [Default: ISO-8859-1]
       --version                      The version of the Rebsta command line tool
    
    resourcesRootDir:   path of directory containing the resources. The directory must
                        contain the ResourceBundle file ("*.properties"), maybe in some 
                        package subdirectories.
    sourceRootDir:      path of directory containing the Java sources. Will contain the
                        generated static text access class, maybe in some package subdirectory.
    resourceBundleName: fully qualified name of the ResourceBundle. Will also be the 
                        full classname of the generated static text access class.
            
## Installation

### Mac OS

    $ cd /usr/local/bin
    $ sudo curl -O https://github.com/abego/rebsta/releases/download/v0.9.3/rebsta-cli-0.9.3-complete.jar
    $ alias rebsta='java -jar /usr/local/bin/rebsta-cli-0.9.3-complete.jar'

_(You may consider putting the "`alias ...`" line into your "`~/.bash_profile`" 
file.)_

### Windows

- Download [https://github.com/abego/rebsta/releases/download/v0.9.3/rebsta-cli-0.9.3-complete.jar](https://github.com/abego/rebsta/releases/download/v0.9.3/rebsta-cli-0.9.3-complete.jar)
- Create batch commands for Rebsta in a directory that is in PATH
  - rebsta.bat: `java -jar /usr/local/lib/rebsta-cli-0.9.3-complete.jar %*`
    
## Motivation

When using ResourceBundles to handle locale-specific strings one typically
writes code like this:

    ResourceBundle bundle = ResourceBundle.getBundle("Texts"); //NON-NLS
    ...
    String buttonTitle = bundle.getString("btnCancel"); //NON-NLS
    ...
    String errorMessage = MessageFormat.format(
                            bundle.getString("fileNotFound", //NON-NLS
                            file.getAbsolutePath());

Rebsta will created a "text access class" (e.g. named `Texts.java`) 
that gives you "static text access" to the strings in the ResourceBundle. 

The above example then looks like this:

    String buttonTitle = Texts.btnCancel();
    ...
    String errorMessage = Texts.fileNotFound(file.getAbsolutePath());

For every string in the ResourceBundle there is a static method (named 
after the key of the string) that returns the localized string. When the
string in the ResourceBundle contains parameters (`{0}`, `{1}`...) the 
static method has parameters too, matching the ones in the string. In that
case the static method also handles the "MessageFormat" stuff.

Running Rebsta on every ResourceBundle change ensures the generated
text access class is always in sync with the resources.

## Advantages

The Static Access approach
- makes code easier to read,
- is less code to write,
- allows you to use code completion to more easily editing code containing 
  localized strings.
  - E.g. type `Texts.`, then your IDE's code completion shortcut and the IDE 
    will present you the list of available strings (/string access methods).
- is less error prone, or detects errors earlier (i.e. already at compile 
  time, not just at runtime):
  - no issues with typos in the key strings
    - E.g. mistype key string "cancel" (e.g. "camcel")
      - classic approach: problem detected at runtime (maybe), 
      - static approach: detected by compiler, as there is no method
        `Texts.camcel()`.
  - no issue with missing or extra parameters for parameterized strings
    - as the string access method has as many parameters as the string in
      the resource the compiler ensures the correct number of parameters
      are passed in. In the classic approach possible errors (too many or
      too few parameters) are detected only at runtime.
  - changes to the ResourceBundle files, like removing a string, changing
    a key or the number of parameters in a string, are directly reflected
    in the text access class. If the changes in the ResourceBundle are not
    also reflected in the source code using the strings the compiler will 
    report the errors.
- makes it easy to detect unused localized strings (IntelliJ IDEA only)
  - IntelliJ IDEA reports methods that are never used. If a static method 
    in the text access class is not used, the string is not used.

## Development

You may check out the Rebsta source code from the [GitHub repository](https://github.com/abego/rebsta).

## Links

- Sources: https://github.com/abego/rebsta
- Twitter: @abego (e.g. for announcement of new releases)

## License

Rebsta is available under a business friendly [MIT license](https://www.abego-software.de/legal/mit-license.html).
