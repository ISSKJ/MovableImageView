PACKAGE=org.dnu.ui.scrollimage
all:
	ant debug
clean:
	ant clean
make run:
	adb uninstall $(PACKAGE)
	adb install bin/*-debug.apk

