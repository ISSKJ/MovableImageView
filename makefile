PACKAGE=org.dnu.ui.test
all:
	ant debug
clean:
	ant clean
make run:
	adb uninstall $(PACKAGE)
	adb install bin/*-debug.apk

