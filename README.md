# sbt-write-output-to-file

This is an sbt plugin that writes the `stdout` of `sbt run` to a file.

## Why?

Of course you could just do

```
$ sbt run > output.log
```

but then you get the sbt logging version of your process's output, complete with a noisy ANSI-coloured `[info]` header on every line. There's no easy way to get hold of the raw output of the process.

## How to use

Add the following to your `project/plugins.sbt` to add the plugin:

```
resolvers += Resolver.bintrayIvyRepo("cb372", "sbt-plugins")
addSbtPlugin("cb372" % "sbt-write-output-to-file" % "0.2")
```

This version will work for both sbt 0.13.x and 1.x.

Then add a line to your `build.sbt` to enable it:

```
enablePlugins(WriteOutputToFile)
```

Now when you execute `run`, the output will be written to a text file instead of being logged to the console.

## Settings

There are a few keys available for configuring the plugin:

```
// Where to save the process's output, defaults to "output.log"
writeOutputToFile_outputFile := file("stdout.txt")

// Whether to append to the file every time the process to runs, defaults to false (i.e. overwrite the file)
writeOutputToFile_append := true 
```

## Notes

The `fork` flag must be set to `true` in order to collect the process's output. The plugin sets it to true automatically, but if you override it and set it back to `false` then the plugin will not work.

`stderr` will not be written to the file. It will show up in the console as usual.

