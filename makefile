PACKAGE=org.dnu.ui.test
all:
	ant release
clean:
	ant clean
make run:
	adb uninstall $(PACKAGE)
	adb install bin/*-release.apk

