import math

class Sprite:
    def getSpriteName(self, orientation, shift):
        shiftstr = ['_inf'] + range(0,8) + ['_sup']
        return '%s_%s%s' % (self.getName(), orientation, shiftstr[shift+1])

    def spriteRange(self, orientation):
        return xrange([-1,0][orientation == 'rtl'],[8,9][orientation == 'rtl'])

    def makeTable(self, orientation):
        print '%s_%s_dispatch:' % (self.getName(), orientation)
        print '\n'.join(['\tdw %s' % self.getSpriteName(orientation, x) for x in self.spriteRange(orientation)])


    def makeGroup(self, orientation):
        print '%s_%s_spritegroup:' % (self.getName(), orientation)

        for shift in self.spriteRange(orientation):
            print '%s:' % self.getSpriteName(orientation, shift)
            self.makeAsm(shift, orientation)
        print ';; end of sprite group %s' % self.getName()

    def makeAsm(self, shift, orientation):
        print '\tlxi h, 0'
        print '\tdad sp'
        print '\tshld sprites_scratch'    

        print '\tmov h, d'        
        print '\tmov l, e'        
        print '\tsphl'
        print '\tlxi b, 0'
        print ';; green'
        self.makeLayer('v', shift, orientation)     # base layer, only green

        print ';;;; black/magenta layer 1'
        print '\tlxi h, $2000'
        print '\tdad d'
        print '\tsphl'
        self.makeLayer('nm', shift, orientation)     # layer 1: magenta + black

        print ';;;; yellow/magenta layer 2'
        print '\tlxi h, $4000'
        print '\tdad d'
        print '\tsphl'
        self.makeLayer('ma', shift, orientation)    # layer 2: magenta + yellow

        print '\tlhld sprites_scratch'
        print '\tsphl'
        print '\tret'

    def makeLayer(self, layerchar, shift, orientation):
        pic = self.getPic(shift, 
                mirror = orientation == 'rtl', 
                prepend = shift == -1, 
                append = shift == 8)


        height = len(pic)
        width = len(pic[0])
        columns = width / 8

        lastb = -1

        for column in xrange(0,columns):
            for y in xrange(0,height,2):
                popor = pic[y][column*8:column*8+8] + pic[y+1][column*8:column*8+8]
                b = self.filter(popor, layerchar)
                if b == 0:
                    print '\tpush b'
                else:
                    if b != lastb:
                        print '\tlxi h, $%04x' % b
                        lastb = b
                    print '\tpush h'
            if column != columns - 1:
                print '\tlxi h, 256+%d\n\tdad sp\n\tsphl' % height
                lastb = -1

    def getPic(self, shift, prepend = False, append = False, mirror = False):
        unshifted = self.getPicRaw()
        if (mirror):  unshifted = map(lambda x: x[::-1], unshifted)
        if (prepend): unshifted = map(lambda x: ' '*8 + x, unshifted)
        if (append):  unshifted = map(lambda x: x + ' '*8, unshifted)
        if (self.isDoubleWidth()):
            unshifted = map(self.doublify, unshifted)
        if shift == 0:
            return unshifted
        else:
            return map(lambda x: ' '*shift + x + ' '*(8-shift), unshifted)
                
    def doublify(self, chars):
        return ''.join([x + x for x in chars])

    def isDoubleWidth(self):
        return False

    def filter(self, chars, charset):
        return reduce(lambda x,y: (x<<1)|y, [[0,1][x in charset] for x in chars])
    

class Ship(Sprite):
    pic = ['      nn        ',
           '      nn        ',
           '    nnnn        ',
           '  nnnnnnnn      ',
           'mmmmmmmmmmmmmmmm',
           'mmmmmmmmmmmmmm  ',
           'vvvvvvvvvvv     ',
           '  vvvvvvvvv     ']

    def getPicRaw(self):
        return self.pic

    def isDoubleWidth(self):
        return True

    def getName(self):
        return "ship"
        
print ';; Automatically generated file'
print ';; see makesprites.py'

a = Ship()
a.makeTable('ltr')
a.makeGroup('ltr')

a.makeTable('rtl')
a.makeGroup('rtl')
    