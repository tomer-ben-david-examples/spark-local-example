# Study spark locally
So you wish to study spark, great! now lets start a spark cluster, just download a 4GB image from cloudera and start a vm every time you need to run a silly example.  Or use your ready made vm's.  **This sounds silly**.  Why not have a template for running spark examples locally and practice with them.  This is exactly what this sample project is about.  You see once you need `lzo` and you will be asked to install hadoop to enable lzo libraries on your machine etc, the aim of this project is to have spark in its lightest weight.

# Study spark inside intellij
The examples can be run smoothly in intellij.

# Examples and csv file analysis locally

If you get `java.lang.UnsatisfiedLinkError: no gplcompression in java.library.path`
then:

https://code.google.com/a/apache-extras.org/p/hadoop-gpl-compression/downloads/list


https://github.com/toddlipcon/hadoop-lzo

```
sudo apt-get install liblzo2-dev
[building lzo](https://code.google.com/a/apache-extras.org/p/hadoop-gpl-compression/wiki/FAQ)
```
