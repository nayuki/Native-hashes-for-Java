# 
# Native hash functions for Java
# 
# Copyright (c) Project Nayuki. (MIT License)
# https://www.nayuki.io/page/native-hash-functions-for-java
# 


# ---- Configuration ----

# Available modes: c, x86, x86-64
MODE = c

# C compiler
CC = gcc

# Where to find jni.h
JAVA_INCLUDE_PATH = /usr/lib/jvm/java-1.6.0-openjdk/include/


# ---- Source files ----

SRC_FILENAMES =  \
    md2-jni.c        \
    md4-jni.c        \
    md5-jni.c        \
    ripemd128-jni.c  \
    ripemd160-jni.c  \
    ripemd256-jni.c  \
    ripemd320-jni.c  \
    sha1-jni.c       \
    sha256-jni.c     \
    sha512-jni.c     \
    tiger-jni.c      \
    whirlpool-jni.c

ifeq "$(MODE)" "c"
    SRC_FILENAMES += \
        md4-compress.c        \
        md5-compress.c        \
        ripemd128-compress.c  \
        ripemd160-compress.c  \
        sha1-compress.c       \
        sha256-compress.c     \
        sha512-compress.c     \
        whirlpool-compress.c
else ifeq "$(MODE)" "x86"
    SRC_FILENAMES += \
        md4-compress-x86.S        \
        md5-compress-x86.S        \
        ripemd128-compress-x86.S  \
        ripemd160-compress-x86.S  \
        sha1-compress-x86.S       \
        sha256-compress-x86.S     \
        sha512-compress-x86.S     \
        whirlpool-compress-x86.S
else ifeq "$(MODE)" "x86-64"
    SRC_FILENAMES += \
        md4-compress-x8664.S        \
        md5-compress-x8664.S        \
        ripemd128-compress-x8664.S  \
        ripemd160-compress-x8664.S  \
        sha1-compress-x8664.S       \
        sha256-compress-x8664.S     \
        sha512-compress-x8664.S     \
        whirlpool-compress-x8664.S
else
    $(error Invalid mode "$(MODE)")
endif

SRC_FILENAMES += \
    md2-compress.c        \
    ripemd256-compress.c  \
    ripemd320-compress.c  \
    tiger-compress.c

SRC_FILES = $(foreach name, $(SRC_FILENAMES), native/$(name))


# ---- Rules ----

LIBFILE = libnayuki-native-hashes.so

all: $(LIBFILE) classes

$(LIBFILE): $(SRC_FILES)
	$(CC) -Wall -I $(JAVA_INCLUDE_PATH) -shared -fPIC -O1 -o $@ $(SRC_FILES)

classes: java/bin
	cd java/src ; javac -cp ../bin -d ../bin nayuki/nativehash/*.java
	cd java/test; javac -cp ../bin -d ../bin nayuki/nativehash/*.java
	cd java/demo; javac -cp ../bin -d ../bin nayuki/nativehash/*.java *.java

java/bin:
	mkdir $@

clean:
	rm -f $(LIBFILE)
	rm -rf java/bin

.PHONY: classes clean
