rem avrdude -p m8 -c pony-stk200 -P lpt1 -U hfuse:w:0xdc:m -U lfuse:w:0xe4:m
avrdude -p m88 -c pony-stk200 -P lpt1 -U flash:w:"boot_atmega88.hex":i -v -V -u
