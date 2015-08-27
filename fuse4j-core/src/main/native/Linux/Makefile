# Recall this is the included Makefile by e.g. x86_64/Makefile

SHELL = /bin/bash

OS = Linux

PWD = $(shell pwd)

# ARCH, JDK_HOME, PREFIX, LIBNAME environment parameters expected set
# in the Maven invocation of this build, see the driver pom at
# $(NATIVEHOME)/pom.xml.  If building locally (i.e. NOT via Maven), 
# need to define these variables yourself, in e.g. x86_64/Makefile.env

ifndef ARCH
    $(error ARCH make variable missing.  Copy $(PWD)/Makefile.env.sample to $(PWD)/Makefile.env and edit)
endif

ifndef JDK_HOME
$(error JDK_HOME make variable missing.  Copy $(PWD)/Makefile.env.sample to $(PWD)/Makefile.env and edit)
endif

ifndef PREFIX
$(error PREFIX make variable missing.  Copy $(PWD)/Makefile.env.sample to $(PWD)/Makefile.env and edit)
endif

ifndef LIBNAME
$(error LIBNAME make variable missing.  Copy $(PWD)/Makefile.env.sample to $(PWD)/Makefile.env and edit)
endif

BASEDIR = $(abspath $(NATIVEHOME)/../../..)

CFLAGS += -Wall -Werror -fPIC #-std=c99

CPPFLAGS += -I$(BASEDIR)/target/native/javah

CPPFLAGS += -I$(JDK_HOME)/include -I$(JDK_HOME)/include/linux

CPPFLAGS += -D_FILE_OFFSET_BITS=64

LDFLAGS += -shared

LDLIBS =  -lfuse

MAINSRCS = $(shell cd $(NATIVEHOME) && ls *.c)

MAINOBJS = $(MAINSRCS:.c=.o)

VPATH = $(NATIVEHOME)

LINK.so = $(CC) $(LDFLAGS) $(LDLIBS)

# the name of the library to build
LIB		= lib$(LIBNAME).so

# link the C objects into a shared library
$(LIB): $(MAINOBJS)
	$(CC) $(LDFLAGS) $^ $(LDLIBS) $(OUTPUT_OPTION)

PREFIXASPATH = $(subst .,/,$(PREFIX))

TARGET_DIR = $(BASEDIR)/src/main/resources/$(PREFIXASPATH)/native/$(OS)/$(ARCH)

default: install

lib: $(LIB)

install: $(LIB)
	@mkdir -p $(TARGET_DIR)
	cp $(LIB) $(TARGET_DIR)

clean:
	-rm $(LIB) *.o

############################## Test/debug targets #######################

show-env:
	@echo JAVA_HOME $(JAVA_HOME)

show-srcs:
	echo $(MAINSRCS)

show-objs:
	echo $(MAINOBJS)

show-lib:
	echo $(LIB)

show-target:
	echo $(TARGET_DIR)

show-install:
	echo $(TARGET_DIR)/$(LIB)

# eof
