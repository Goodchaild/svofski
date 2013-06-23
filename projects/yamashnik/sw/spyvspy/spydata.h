#pragma once

enum SpyRequestCode {
    F0E_SELECT_DISK         = 0,                    
    F0F_OPEN_FILE,          
    F10_CLOSE_FILE,         
    F11_SEARCH_FIRST,       
    F12_SEARCH_NEXT,        
    F13_DELETE,             
    F14_SEQ_READ,           
    F15_SEQ_WRITE,          
    F16_CREAT,              
    F17_RENAME,             
    F18_GETLOGINVECTOR,     
    F19_GET_CURRENT_DRIVE,  

    F1B_GET_ALLOC_INFO      = 0x0D,     
    F21_RANDOM_READ         = 0x13,     
    F22_RANDOM_WRITE,       
    F23_GET_FILE_SIZE,      
    F24_SET_RANDOM_RECORD,  

    F26_RANDOM_BLOCK_WRITE  = 0x18,
    F27_RANDOM_BLOCK_READ,  
    F28_RANDOM_WRITE_ZERO,  

    F2F_ABS_SECTOR_READ     = 0x21, 
    F30_ABS_SECTOR_WRITE    = 0x22, 
};

enum {
    REQ_BYTE = 1, REQ_WORD, REQ_FCB, REQ_DMA, REQ_END = 0,
};

struct FCB {
/*  0 */    uint8_t     Drive;
/*  1 */    uint8_t     NameExt[11];
/* 12 */    uint8_t     ExtentNumberLo;
/* 14 */    uint8_t     ExtentNumberHi;
            uint8_t     RecordSizeLo;
            uint8_t     RecordSizeHi;
/* 16 */    uint32_t    FileSize;

/* 20 */    uint16_t    Date;
/* 22 */    uint16_t    Time;

/* 24 */    uint8_t     DeviceId;           // 40h + Drive (A=40h)
/* 25 */    uint8_t     DirectoryLocation;
/* 26 */    uint16_t    TopCluster;
/* 28 */    uint16_t    LastClusterAccessed;
/* 30 */    uint16_t    RelativeLocation;

/* 32 */    uint8_t     CurrentRecord;      // Curent Record within Extent
/* 33 */    uint32_t    RandomRecord;
/* 36 */    uint32_t    Padding;
/* 40 */    uint8_t     Padding2[3];

    FCB() {
        memset(this, 0, sizeof(FCB));
    }

    uint16_t RecordSize() const {
        return (RecordSizeHi << 8) | RecordSizeLo;
    }

    uint16_t GetExtent() const {
        return (ExtentNumberHi << 8) | ExtentNumberLo;
    }

    void SetExtent(const uint16_t extent) {
        ExtentNumberLo = extent & 0xff;
        ExtentNumberHi = (extent >> 8) & 0xff;
    }

    int SetNameExt(const char* fname) {
        char* shrt = (shrt = strrchr(fname, '/')) ? (shrt + 1) : (char *)fname;

        char* dot = strchr(shrt, '.');
        int namelen = dot == 0 ? strlen(shrt) : dot - shrt;
        int extlen = strlen(shrt) - namelen - 1;
        if (namelen > 8 || extlen > 3) {
            info("ERROR: filenames must be 8.3\n");
            return 0;
        }

        memset(NameExt, ' ', sizeof(NameExt));
        memcpy(NameExt, shrt, namelen);
        if (extlen > 0) {
            memcpy(NameExt + 8, shrt + namelen + 1, extlen);
        }       

        return 1;
    }

    void GetFileName(char* normalname) {
        int i = 0;
        for (int c; i < 8 && (c = NameExt[i]) != ' '; normalname[i] = c, i++);
        normalname[i++] = '.';
        for (int s = 8, c; s < 11 && (c = NameExt[s]) != ' '; normalname[i] = c, i++, s++);
        normalname[i] = 0;
    }
} __attribute__((packed));

struct DIRENT {
            uint8_t AlwaysFF;
/*  0 */    char NameExt[11];
/* 11 */    uint8_t Attrib;
/* 12 */    uint8_t Space[10];
/* 22 */    uint16_t Time;
/* 24 */    uint16_t Date;
/* 26 */    uint16_t Cluster;
/* 28 */    uint32_t FileSize;

    DIRENT() {
        memset(this, 0, sizeof(DIRENT));
        AlwaysFF = 0xff;
    }

    void InitFromFCB(FCB* fcb) {
        memcpy(NameExt, fcb->NameExt, sizeof(NameExt));
        FileSize = fcb->FileSize;
    }
} __attribute__((packed));;