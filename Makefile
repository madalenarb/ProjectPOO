# Makefile for Java project

# Java compiler
JC = javac

# Java compiler flags
JFLAGS = -g --release 8

# Source directories
SRCDIRS = graph main

# Java source files (excluding Make.java)
SRCS = $(foreach dir,$(SRCDIRS),$(filter-out main/Make.java,$(wildcard $(dir)/*.java)))

# Java class directory
CLASSDIR = classes

# Java class files
CLASSES = $(SRCS:%.java=$(CLASSDIR)/%.class)

# Executable JAR file
JARFILE = project.jar

# Manifest file
MANIFEST = manifest.txt

# Main class
MAINCLASS = main.Main

# Default target
default: $(JARFILE)

# Compile Java source files into class files
$(CLASSDIR)/%.class: %.java | $(CLASSDIR)
	$(JC) $(JFLAGS) -d $(CLASSDIR) $<

# Create the class directory
$(CLASSDIR):
	mkdir -p $(CLASSDIR)

# Create the executable JAR file
$(JARFILE): $(CLASSES) $(MANIFEST)
	jar cfm $(JARFILE) $(MANIFEST) -C $(CLASSDIR) .

# Create the manifest file
$(MANIFEST):
	echo "Main-Class: $(MAINCLASS)" > $(MANIFEST)
	echo "" >> $(MANIFEST)

# Clean generated files
clean:
	$(RM) -r $(CLASSDIR) $(JARFILE) $(MANIFEST)

.PHONY: clean
