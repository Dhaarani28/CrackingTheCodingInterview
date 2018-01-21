To use the classes in this directory in a class within another directory, you have to add this directory to the classpath. This is necessary for both, the compilation tool (`javac`) and the execution tool (`java`).

The easiest thing to achieve this is to add this directory to the `CLASSPATH` environment variable.

The classes in this directory are in the default package (i.e. they do not declare an explicit package). Thus, these classes can be used by any other class in the default package without any `import` statement (as mentioned, requirement for this is that this directory is in the classpath for `javac` and `java`).
