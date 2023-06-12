# Java compiler
JC = javac

# Java compiler flags
JFLAGS = -g --release 8

# Java doc generator
JAVADOC = javadoc

# Source directories
SRCDIRS = ant cycle event graph main

# Java source files (excluding Main.java)
SRCS = $(foreach dir,$(SRCDIRS),$(wildcard $(dir)/*.java))

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

# Documentation directory
DOCDIR = javadoc

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

# Generate Javadoc
javadoc: $(SRCS)
	$(JAVADOC) -d $(DOCDIR) $(SRCS)

# Open Javadoc
openjavadocUbuntu: javadoc
	xdg-open $(DOCDIR)/index.html

openjavadocMac: javadoc
	open $(DOCDIR)/index.html

openjavadocWindows: javadoc
	start $(DOCDIR)/index.html

# Open Javadoc in a browser

# Clean generated files
clean:
	$(RM) -r $(CLASSDIR) $(JARFILE) $(MANIFEST) $(DOCDIR)

.PHONY: clean javadoc openjavadocUbuntu openjavadocMac openjavadocWindows
