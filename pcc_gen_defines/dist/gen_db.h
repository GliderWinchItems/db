// Defines from database pcc
// 2015-07-06 23:16:24.162
#ifndef  DATABASE_DEFINES
#define  DATABASE_DEFINES


#define CANID_COUNT 93
#define  CANID_CMD_TENSION_0      0xE8000000  // TENSION_0      : Tension_0: Default command canid
#define  CANID_TENSION_0          0x48000000  // TENSION_1      : Tension_0: Default measurement canid
#define  CANID_TENSION_1          0x38000000  // TENSION_1      : Tension_1: calibrated tension, polled by time msg
#define  CANID_CMD_TENSION_1      0x05C0000C  // TENSION_1      : Tension_1: Tension_1: Command code: [0] command code, [1]-[8] depends on code
#define  CANID_CMD_TENSION_2      0x05C0001C  // TENSION_2      : Tension_2: Tension_2: Command code: [0] command code, [1]-[8] depends on code
#define  CANID_TENSION_2          0x38800000  // TENSION_2      : Tension_2: calibrated tension, polled by time msg
#define  CANID_MOTOR_1            0x2D000000  // MOTOR_1        : MOTOR_1: Motor speed
#define  CANID_CMD_CABLE_ANGLE_0  0x05D00000  // CABLE_ANGLE_0  : Cable_Angle0: Default measurement canid
#define  CANID_CMD_CABLE_ANGLE_1  0x05F00000  // CABLE_ANGLE_1  : Cable_Angle1: [0] command code, [1]-[8] depends on code
#define  CANID_CABLE_ANGLE_1      0x3A000000  // CABLE_ANGLE_1  : Cable_Angle1: for drum #1
#define  CANID_CABLE_ANGLE_ALARM  0x2B000000  // CABLE_ANGLE_1  : Cable_Angle1: unreliable for drum #1
#define  CANID_CMD_ENGINE_SENSOR  0x80600000  // ENGINE_SENSOR  : Engine: code: [0] command code, [1]-[8] depends on code
#define  CANID_ENG_RPMMANIFOLD    0x40600000  // ENGINE_SENSOR  : Engine: rpm:manifold pressure
#define  CANID_ENG_TEMP           0x70600000  // ENGINE_SENSOR  : Engine: thermistor converted to temp
#define  CANID_ENG_THERMTHROTL    0x60600000  // ENGINE_SENSOR  : Engine: thermistor:throttle pot
#define  CANID_ENG_THROTTLE       0x50600000  // ENGINE_SENSOR  : Engine: throttle
#define  CANID_CMD_TIMESYNC       0xC1C00000  // TIMESYNC       : Time: CANID Command GPS
#define  CANID_FIX_HT_TYP_NSAT    0xB1C00000  // TIMESYNC       : Time: GPS winch fix: heigth:type fix:number sats
#define  CANID_FIX_LATLON         0xA1C00000  // TIMESYNC       : Time: GPS winch fix: lattitude:longitude
#define  CANID_LG_ER1             0xD1C00004  // TIMESYNC       : Time: 1st code  CANID-UNITID_CO_OLI GPS checksum error
#define  CANID_LG_ER2             0xD1C00014  // TIMESYNC       : Time: 2nd code  CANID-UNITID_CO_OLI GPS Fix error
#define  CANID_LG_ER3             0xD1C00024  // TIMESYNC       : Time: 3rd code  CANID-UNITID_CO_OLI GPS Time out of step
#define  CANID_TIMESYNC           0x00200000  // TIMESYNC       : Time: GPS time sync distribution msg
#define  CANID_MC_DRUM_SELECT     0xD0800814  // MC             : MC: Drum selection
#define  CANID_MC_MOTOR_1_KPALIVE 0xA0800000  // MC             : MC: Curtis Controller keepalive
#define  CANID_MC_REQUEST_PARAM   0xD0800824  // MC             : MC: Request parameters from HC
#define  CANID_MC_CONTACTOR       0x23000000  // MC             : MC: Contactor OPEN/CLOSE
#define  CANID_MC_BRAKES          0x21000000  // MC             : MC: Brakes APPLY/RELEASE
#define  CANID_MC_GUILLOTINE      0x22000000  // MC             : MC: Fire guillotine
#define  CANID_MC_RQ_LAUNCH_PARAM 0x27000000  // MC             : MC: Fire request launch parameters
#define  CANID_TIME_MSG           0x20000000  // MC             : MC: Time msg/Group polling
#define  CANID_MC_STATE           0x26000000  // MC             : MC: Launch state msg
#define  CANID_MC_TORQUE          0x25800000  // MC             : MC: Motor torque
#define  CANID_CP_CTL_RMT         0x29000000  // CP             : Control Panel: Control lever remote
#define  CANID_CP_CTL_LCL         0x29200000  // CP             : Control Panel: Control lever local
#define  CANID_CP_CTL_IN_RMT      0x24C00000  // CP             : Control Panel: Control lever remote: input
#define  CANID_CP_CTL_IN_LCL      0x25000000  // CP             : Control Panel: Control lever  local: input
#define  CANID_CP_CTL_OUT_RMT     0x2A000000  // CP             : Control Panel: Control lever output
#define  CANID_SE2H_ADC2_HistA    0xD0800044  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:SE2: ADC2 HistogramA tx: request count, switch buffers; rx send count
#define  CANID_SE2H_ADC2_HistB    0xD0800054  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:SE2: ADC2 HistogramB tx: bin number, rx: send bin count
#define  CANID_SE2H_ADC3_ADC2_RD  0xD0800064  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:SE2: ADC3 ADC2 readings readout
#define  CANID_SE2H_ADC3_HistA    0xD0800024  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:SE2: ADC3 HistogramA tx: request count, switch buffers. rx: send count
#define  CANID_SE2H_ADC3_HistB    0xD0800034  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:E2: ADC3 HistogramB tx: bin number, rx: send bin count
#define  CANID_SE2H_COUNTERnSPEED 0x30800000  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:SE2: (Lower sheave) Count and speed
#define  CANID_SE2H_ERROR1        0xD0800014  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:SE2: error1
#define  CANID_SE2H_ERROR2        0xD0800004  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:SE2: error2
#define  CANID_CMD_LOWERSHV       0xD0800000  // SHAFT_LOWERSHV : Shaft encoder: Lower sheave:SE2: Command CAN: send commands to subsystem
#define  CANID_SE3H_ADC2_HistA    0xD0A00044  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave: SE3: ADC2 HistogramA tx: request count, switch buffers; rx send count
#define  CANID_SE3H_ADC2_HistB    0xD0A00054  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave:SE3: ADC2 HistogramB tx: bin number, rx: send bin count
#define  CANID_SE3H_ADC3_ADC2_RD  0xD0A00064  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave:SE3: ADC3 ADC2 readings readout
#define  CANID_SE3H_ADC3_HistA    0xD0A00024  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave:SE3: ADC3 HistogramA tx: request count, switch buffers. rx: send count
#define  CANID_SE3H_ADC3_HistB    0xD0A00034  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave:SE3: ADC3 HistogramB tx: bin number, rx: send bin count
#define  CANID_SE3H_COUNTERnSPEED 0x30A00000  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave:SE3: (upper sheave) Count and Speed
#define  CANID_SE3H_ERROR1        0xD0A00014  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave:SE3: error1
#define  CANID_SE3H_ERROR2        0xD0A00004  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave:SE3: error2
#define  CANID_CMD_UPPERSHV       0xD0600000  // SHAFT_UPPERSHV : Shaft encoder: Upper sheave:SE3: Command CAN: send commands to subsystem
#define  CANID_SE4H_ADC2_HistA    0xD0400044  // DRIVE_SHAFT    : Drive shaft: ADC2 HistogramA tx: request count, switch buffers; rx send count
#define  CANID_SE4H_ADC2_HistB    0xD0400054  // DRIVE_SHAFT    : Drive shaft: ADC2 HistogramB tx: bin number, rx: send bin count
#define  CANID_SE4H_ADC3_ADC2_RD  0xD0400064  // DRIVE_SHAFT    : Drive shaft: ADC3 ADC2 readings readout
#define  CANID_SE4H_ADC3_HistA    0xD0400024  // DRIVE_SHAFT    : Drive shaft: ADC3 HistogramA tx: request count, switch buffers. rx: send count
#define  CANID_SE4H_ADC3_HistB    0xD0400034  // DRIVE_SHAFT    : Drive shaft: ADC3 HistogramB tx: bin number, rx: send bin count
#define  CANID_CMD_DRIVE_SHAFT    0xD0400000  // DRIVE_SHAFT    : Drive shaft: Command CAN: send commands to subsystem
#define  CANID_SE4H_COUNTERnSPEED 0x30400000  // DRIVE_SHAFT    : Drive shaft: (drive shaft) count and speed
#define  CANID_SE4H_ERROR1        0xD0400014  // DRIVE_SHAFT    : Drive shaft: [2]elapsed_ticks_no_adcticks<2000 ct  [3]cic not in sync
#define  CANID_SE4H_ERROR2        0xD0400004  // DRIVE_SHAFT    : Drive shaft: [0]encode_state er ct [1]adctick_diff<2000 ct
#define  CANID_TILT_ALARM         0x02800000  // TILT_SENSE     : Tilt: alarm: Vector angle exceeds limit
#define  CANID_TILT_ANGLE         0x42E00000  // TILT_SENSE     : Tilt: Calibrated angles (X & Y)
#define  CANID_TILT_XYZ           0x42800000  // TILT_SENSE     : Tilt:Calibrated to angle: x,y,z tilt readings
#define  CANID_TILT_XYZ_CAL       0xFFFFFFCC  // TILT_SENSE     : Tilt:CANID: Raw tilt ADC readings
#define  CANID_TILT_XYZ_RAW       0x4280000C  // TILT_SENSE     : Tilt:Tilt:Raw tilt ADC readings
#define  CANID_CMD_TILT           0x42C00000  // TILT_SENSE     : Tilt:Command CANID
#define  CANID_HB_GATEWAY         0xE0200000  // GATEWAY        : Gateway: Heartbeat
#define  CANID_HB_TENSION_0       0xE0400000  // TENSION_0      : Tension_0: Heartbeat
#define  CANID_HB_TENSION_1       0xE0600000  // TENSION_1      : Tension_1: Heartbeat
#define  CANID_HB_TENSION_2       0xE0800000  // TENSION_2      : Tension_2: Heartbeat
#define  CANID_HB_CABLE_ANGLE_1   0xE0A00000  // CABLE_ANGLE_1  : Cable_Angle1: Heartbeat
#define  CANID_CMD_SANDBOX_1      0x28E00000  // SANDBOX_1      : HC: SANDBOX_1: Launch parameters
#define  CANID_UNIT_2             0x00400000  // UNIT_2         : Sensor unit: Drive shaft encoder
#define  CANID_UNIT_3             0x00600000  // UNIT_3         : Sensor unit: Engine
#define  CANID_UNIT_4             0x00800000  // UNIT_4         : Sensor unit: Lower sheave shaft encoder
#define  CANID_UNIT_5             0x00A00000  // UNIT_5         : Sensor unit: Upper sheave shaft encoder
#define  CANID_UNIT_8             0x01000000  // UNIT_8         : Sensor unit: Level wind
#define  CANID_UNIT_9             0x01200000  // UNIT_9         : Sensor unit: XBee receiver #1
#define  CANID_UNIT_A             0x0140000C  // UNIT_A         : Sensor unit: XBee receiver #2
#define  CANID_UNIT_B             0x0160000C  // UNIT_B         : Display driver/console
#define  CANID_UNIT_C             0x0180000C  // UNIT_C         : CAWs Olimex board
#define  CANID_UNIT_D             0x01A0000C  // UNIT_D         : POD board sensor prototype ("6" marked on board)
#define  CANID_UNIT_E             0x01C0000C  // UNIT_E         : Logger: sensor board w ublox gps & SD card
#define  CANID_UNIT_F             0x01E0000C  // UNIT_F         : Tension_1 & Cable_angle_1 Unit
#define  CANID_UNIT_10            0x0200000C  // UNIT_10        : Gateway: 
#define  CANID_UNIT_19            0x0280000C  // UNIT_19        : Master Controller
#define  CANID_UNIT_99            0xFFFFFF14  // UNIT_99        : Dummy for missing CAN IDs
#define  CANID_DUMMY              0xFFFFFFFC  // UNIT_NU        : Dummy ID: Lowest priority possible (Not Used)

#define NUMBER_TYPE_COUNT 17
#define  TYP_S8                  1         // 1             int8_t,   signed char, 1 byte
#define  TYP_U8                  2         // 1            uint8_t, unsigned char, 1 byte
#define  TYP_S16                 3         // 2            int16_t,   signed short, 2 bytes
#define  TYP_U16                 4         // 2           uint16_t, unsigned short, 2 bytes
#define  TYP_S32                 5         // 4            int32_t,   signed int, 4 bytes
#define  TYP_U32                 6         // 4           uint32_t, unsigned int, 4 bytes
#define  TYP_S64_L               7         // 4            int64_t,   signed long long, low  order 4 bytes
#define  TYP_S64_H               8         // 4            int64_t,   signed long long, high order 4 bytes
#define  TYP_U64_L               9         // 4           uint64_t, unsigned long long, low  order 4 bytes
#define  TYP_U64_H               10        // 4           uint64_t, unsigned long long, high order 4 bytes
#define  TYP_FLT                 11        // 4           float, 4 bytes
#define  TYP_12FLT               12        // 2           half-float, 2 bytes
#define  TYP_34FLT               13        // 3           3/4-float, 3 bytes
#define  TYP_DBL_L               14        // 4           double, low  order 4 bytes
#define  TYP_DBL_H               15        // 4           double, high order 4 bytes
#define  TYP_ASC                 16        // 4           ascii chars
#define  TYP_CANID               17        // 1           CANID (handled differently than a U32)

#define CMD_CODES_COUNT 31
#define  LDR_SET_ADDR            1         // 5 Set address pointer (not FLASH) (bytes 2-5):  Respond with last written address.
#define  LDR_SET_ADDR_FL         2         // 5 Set address pointer (FLASH) (bytes 2-5):  Respond with last written address.
#define  LDR_CRC                 3         // 8 Get CRC: 2-4 = count; 5-8 = start address; Reply CRC 2-4 na, 5-8 computed CRC 
#define  LDR_ACK                 4         // 1 ACK: Positive acknowledge (Get next something)
#define  LDR_NACK                5         // 1 NACK: Negative acknowledge (So? How do we know it is wrong?)
#define  LDR_JMP                 6         // 5 Jump: to address supplied (bytes 2-5)
#define  LDR_WRBLK               7         // 1 Done with block: write block with whatever you have.
#define  LDR_RESET               8         // 1 RESET: Execute a software forced RESET
#define  LDR_XON                 9         // 1 Resume sending
#define  LDR_XOFF                10        // 1 Stop sending
#define  LDR_FLASHSIZE           11        // 1 Get flash size; bytes 2-3 = flash block size (short)
#define  LDR_ADDR_OOB            12        // 1 Address is out-of-bounds
#define  LDR_DLC_ERR             13        // 1 Unexpected DLC
#define  LDR_FIXEDADDR           14        // 5 Get address of flash with fixed loader info (e.g. unique CAN ID)
#define  LDR_RD4                 15        // 5 Read 4 bytes at address (bytes 2-5)
#define  LDR_APPOFFSET           16        // 5 Get address where application begins storing.
#define  LDR_HIGHFLASHH          17        // 5 Get address of beginning of struct with crc check and CAN ID info for app
#define  LDR_HIGHFLASHP          18        // 8 Get address and size of struct with app calibrations, parameters, etc.
#define  LDR_ASCII_SW            19        // 2 Switch mode to send printf ASCII in CAN msgs
#define  LDR_ASCII_DAT           20        // 3-8 [1]=line position;[2]-[8]=ASCII chars
#define  LDR_WRVAL_PTR           21        // 2-8 Write: 2-8=bytes to be written via address ptr previous set.
#define  LDR_WRVAL_PTR_SIZE      22        // Write data payload size
#define  LDR_WRVAL_AI            23        // 8 Write: 2=memory area; 3-4=index; 5-8=one 4 byte value
#define  LDR_SQUELCH             24        // 8 Send squelch sending tick ct: 2-8 count
#define  CMD_GET_IDENT           30        // Get parameter using indentification name/number in byte [1]
#define  CMD_PUT_IDENT           31        // Put parameter using indentification name/number in byte [1]
#define  CMD_GET_INDEX           32        // Get parameter using index name/number in byte [1]
#define  CMD_PUT_INDEX           33        // Put parameter using index name/number in byte [1]
#define  CMD_REVERT              34        // Revert (re-initialize) working parameters/calibrations/CANIDs back to stored non-volatile values
#define  CMD_SAVE                35        // Write current working parameters/calibrations/CANIDs to non-volatile storage
#define  CMD_GET_READING         36        // Send a reading code specified in byte [1]

#define PAYLOAD_TYPE_COUNT 19
#define  FF                      1         //  [0]-[3]: Full Float                            
#define  FF_FF                   2         //  [0]-[3]: Full Float; [4]-[7]: Full Float       
#define  U32                     3         //  [0]-[3]: uint32_t                              
#define  U32_U32                 4         //  [0]-[3]: uint32_t; [4]-[7]: uint32_t           
#define  U8_U32                  5         //  [0]: uint8_t; [1]-[4]: uint32_t                
#define  S32                     6         //  [0]-[3]: int32_t                               
#define  S32_S32                 7         //  [0]-[3]: int32_t; [4]-[7]: int32_t             
#define  U8_S32                  8         //  [0]: int8_t; [4]-[7]: int32_t                  
#define  HF                      9         //  [0]-[1]: Half-Float                            
#define  F34F                    10        //  [0]-[2]: 3/4-Float                             
#define  xFF                     11        //  [1]-[4]: Full-Float, first   byte  skipped     
#define  xxFF                    12        //  [1]-[4]: Full-Float, first 2 bytes skipped     
#define  xxU32                   13        //  [1]-[4]: uint32_t, first 2 bytes skipped       
#define  xxS32                   14        //  [1]-[4]: int32_t, first 2 bytes skipped        
#define  u8_u8_U32               15        //  [0]:[1]:[2]-[5]: uint8_t,uint8_t,uint32_t,     
#define  u8_u8_S32               16        //  [0]:[1]:[2]-[5]: uint8_t,uint8_t, int32_t,     
#define  u8_u8_FF                17        //  [0]:[1]:[2]-[5]: uint8_t,uint8_t, Full Float,  
#define  U16                     18        //  [0]:[1]:[2]uint16_t                            
#define  S16                     19        //  [0]:[1]:[2] int16_t                            

#define PARAM_LIST_COUNT 92	// TOTAL COUNT OF PARAMETER LIST

#define  TENSION_LIST_CRC        	0         // Tension: CRC for tension list                   
#define  TENSION_LIST_VERSION    	1         // Tension: Version number for Tension List        
#define  TENSION_AD7799_1_OFFSET 	2         // Tension: AD7799 #1 offset                       
#define  TENSION_AD7799_1_SCALE  	3         // Tension: AD7799 #1 Scale (convert to kgf)       
#define  TENSION_THERM1_CONST_B  	4         // Tension: Thermistor1 param: constant B          
#define  TENSION_THERM1_R_SERIES 	5         // Tension: Thermistor1 param: Series resistor, fixed (K ohms)
#define  TENSION_THERM1_R_ROOMTMP	6         // Tension: Thermistor1 param: Thermistor room temp resistance (K ohms)
#define  TENSION_THERM1_REF_TEMP 	7         // Tension: Thermistor1 param: Reference temp for thermistor
#define  TENSION_THERM1_TEMP_OFFSET	8         // Tension: Thermistor1 param: Thermistor temp offset correction (deg C)
#define  TENSION_THERM1_TEMP_SCALE	9         // Tension: Thermistor1 param: Thermistor temp scale correction
#define  TENSION_THERM2_CONST_B  	10        // Tension: Thermistor2 param: constant B          
#define  TENSION_THERM2_R_SERIES 	11        // Tension: Thermistor2 param: Series resistor, fixed (K ohms)
#define  TENSION_THERM2_R_ROOMTMP	12        // Tension: Thermistor2 param: Thermistor room temp resistance (K ohms)
#define  TENSION_THERM2_REF_TEMP 	13        // Tension: Thermistor2 param: Reference temp for thermistor
#define  TENSION_THERM2_TEMP_OFFSET	14        // Tension: Thermistor2 param: Thermistor temp offset correction (deg C)
#define  TENSION_THERM2_TEMP_SCALE	15        // Tension: Thermistor2 param: Thermistor temp scale correction
#define  TENSION_THERM1_POLY_COEF_0	16        // Tension: Thermistor1 param: Load-Cell polynomial coefficient 0 (offset)
#define  TENSION_THERM1_POLY_COEF_1	17        // Tension: Thermistor1 param: Load-Cell polynomial coefficient 1 (scale)
#define  TENSION_THERM1_POLY_COEF_2	18        // Tension: Thermistor1 param: Load-Cell polynomial coefficient 2 (x^2)
#define  TENSION_THERM1_POLY_COEF_3	19        // Tension: Thermistor1 param: Load-Cell polynomial coefficient 3 (x^3)
#define  TENSION_THERM2_POLY_COEF_0	20        // Tension: Thermistor2 param: Load-Cell polynomial coefficient 0 (offset)
#define  TENSION_THERM2_POLY_COEF_1	21        // Tension: Thermistor2 param: Load-Cell polynomial coefficient 1 (scale)
#define  TENSION_THERM2_POLY_COEF_2	22        // Tension: Thermistor2 param: Load-Cell polynomial coefficient 2 (x^2)
#define  TENSION_THERM2_POLY_COEF_3	23        // Tension: Thermistor2 param: Load-Cell polynomial coefficient 3 (x^3)
#define  TENSION_HEARTBEAT_CT    	24        // Tension: Heart-Beat: Count of time ticks between autonomous msgs
#define  TENSION_DRUM_NUMBER     	25        // Tension: Drum system number for this function instance
#define  TENSION_DRUM_POLL_BIT   	26        // Tension: Drum system poll 1st byte bit for function instance
#define  TENSION_DRUM_FUNCTION_BIT	27        // Tension: Drum system poll 2nd byte bit for this type of function
#define  TENSION_CANPRM_TENSION  	28        // Tension: CANID: can msg tension                 
#define  TENSION_CANPRM_GROUP_POLL	29        // Tension: CANID: msg that polls group (drum) functions
#define  TENSION_CANPRM_TIMESYNC 	30        // Tension: CANID: msg for time syncing local clocks
#define  TENSION_ASCII_0         	31        // Tension: ASCII text: 4 chars [0]                
#define  TENSION_ASCII_1         	32        // Tension: ASCII text: 4 chars [1]                
#define  TENSION_ASCII_2         	33        // Tension: ASCII text: 4 chars [2]                
#define  TENSION_ASCII_3         	34        // Tension: ASCII text: 4 chars [3]                
#define  TENSION_TEST_DOUBLE_L   	35        // Tension: Test: double low  order 4 bytes        
#define  TENSION_TEST_DOUBLE_H   	36        // Tension: Test: double high order 4 bytes        
#define  TENSION_TEST_U64_L      	37        // Tension: Test: unsigned long long, low  order 4 bytes
#define  TENSION_TEST_U64_H      	38        // Tension: Test: unsigned long long, high order 4 bytes
#define  TENSION_TEST_S64_L      	39        // Tension: Test:   signed long long, low  order 4 bytes
#define  TENSION_TEST_S64_H      	40        // Tension: Test:   signed long long, high order 4 bytes

#define PARAM_LIST_CT_TENSION	41	// Count of same FUNCTION_TYPE in preceing list

#define  CABLE_ANGLE_LIST_CRC    	0         // Cable Angle: CRC for cable angle list           
#define  CABLE_ANGLE_LIST_VERSION	1         // Cable Angle: Version number for Cable Angle List
#define  CABLE_ANGLE_AD7799_2_OFFSET	2         // Cable Angle: AD7799 #2 offset                   
#define  CABLE_ANGLE_AD7799_2_SCALE	3         // Cable Angle: AD7799 #2 Scale (convert to kgf)   
#define  CABLE_ANGLE_THERM1_CONST_B	4         // Cable Angle: Thermistor1 param: constant B      
#define  CABLE_ANGLE_THERM1_R_SERIES	5         // Cable Angle: Thermistor1 param: Series resistor, fixed (K ohms)
#define  CABLE_ANGLE_THERM1_R_ROOMTMP	6         // Cable Angle: Thermistor1 param: Thermistor room temp resistance (K ohms)
#define  CABLE_ANGLE_THERM1_REF_TEMP	7         // Cable Angle: Thermistor1 param: Reference temp for thermistor
#define  CABLE_ANGLE_THERM1_TEMP_OFFSET	8         // Cable Angle: Thermistor1 param: Thermistor temp offset correction (deg C)
#define  CABLE_ANGLE_THERM1_TEMP_SCALE	9         // Cable Angle: Thermistor1 param: Thermistor temp scale correction
#define  CABLE_ANGLE_THERM2_CONST_B	10        // Cable Angle: Thermistor2 param: constant B      
#define  CABLE_ANGLE_THERM2_R_SERIES	11        // Cable Angle: Thermistor2 param: Series resistor, fixed (K ohms)
#define  CABLE_ANGLE_THERM2_R_ROOMTMP	12        // Cable Angle: Thermistor2 param: Thermistor room temp resistance (K ohms)
#define  CABLE_ANGLE_THERM2_REF_TEMP	13        // Cable Angle: Thermistor2 param: Reference temp for thermistor
#define  CABLE_ANGLE_THERM2_TEMP_OFFSET	14        // Cable Angle: Thermistor2 param: Thermistor temp offset correction (deg C)
#define  CABLE_ANGLE_THERM2_TEMP_SCALE	15        // Cable Angle: Thermistor2 param: Thermistor temp scale correction
#define  CABLE_ANGLE_THERM1_POLY_COEF_0	16        // Cable Angle: Thermistor1 param: Load-Cell polynomial coefficient 0 (offset)
#define  CABLE_ANGLE_THERM1_POLY_COEF_1	17        // Cable Angle: Thermistor1 param: Load-Cell polynomial coefficient 1 (scale)
#define  CABLE_ANGLE_THERM1_POLY_COEF_2	18        // Cable Angle: Thermistor1 param: Load-Cell polynomial coefficient 2 (x^2)
#define  CABLE_ANGLE_THERM1_POLY_COEF_3	19        // Cable Angle: Thermistor1 param: Load-Cell polynomial coefficient 3 (x^3)
#define  CABLE_ANGLE_THERM2_POLY_COEF_0	20        // Cable Angle: Thermistor2 param: Load-Cell polynomial coefficient 0 (offset)
#define  CABLE_ANGLE_THERM2_POLY_COEF_1	21        // Cable Angle: Thermistor2 param: Load-Cell polynomial coefficient 1 (scale)
#define  CABLE_ANGLE_THERM2_POLY_COEF_2	22        // Cable Angle: Thermistor2 param: Load-Cell polynomial coefficient 2 (x^2)
#define  CABLE_ANGLE_THERM2_POLY_COEF_3	23        // Cable Angle: Thermistor2 param: Load-Cell polynomial coefficient 3 (x^3)
#define  CABLE_ANGLE_HEARTBEAT_CT	24        // Cable Angle: Heart-Beat: Count of time ticks between autonomous msgs
#define  CABLE_ANGLE_DRUM_NUMBER 	25        // Cable Angle: Drum system number for this function instance
#define  CABLE_ANGLE_DRUM_POLL_BIT	26        // Cable Angle: Drum system poll 1st byte bit for function instance
#define  CABLE_ANGLE_DRUM_FUNCTION_BIT	27        // Cable Angle: Drum system poll 2nd byte bit for this type of function
#define  CABLE_ANGLE_CANPRM_TENSION	28        // Cable Angle: CANID: can msg tension             
#define  CABLE_ANGLE_CANPRM_GROUP_POLL	29        // Cable Angle: CANID: msg that polls group (drum) functions
#define  CABLE_ANGLE_CANPRM_TIMESYNC	30        // Cable Angle: CANID: msg for time syncing local clocks
#define  CABLE_ANGLE_ASCII_0     	31        // Cable Angle: ASCII text: 4 chars [0]            
#define  CABLE_ANGLE_ASCII_1     	32        // Cable Angle: ASCII text: 4 chars [1]            
#define  CABLE_ANGLE_ASCII_2     	33        // Cable Angle: ASCII text: 4 chars [2]            
#define  CABLE_ANGLE_ASCII_3     	34        // Cable Angle: ASCII text: 4 chars [3]            
#define  CABLE_ANGLE_MIN_TENSION 	35        // Cable Angle: Minimum tension required (units to match)
#define  CABLE_ANGLE_RATE_CT     	36        // Cable Angle: Rate count: Number of tension readings between cable angle msgs
#define  CABLE_ANGLE_ALARM_REPEAT	37        // Cable Angle: Number of times alarm msg is repeated
#define  CABLE_ANGLE_CALIB_COEF_0	38        // Cable Angle: Cable angle polynomial coefficient 0
#define  CABLE_ANGLE_CALIB_COEF_1	39        // Cable Angle: Cable angle polynomial coefficient 1
#define  CABLE_ANGLE_CALIB_COEF_2	40        // Cable Angle: Cable angle polynomial coefficient 2
#define  CABLE_ANGLE_CALIB_COEF_3	41        // Cable Angle: Cable angle polynomial coefficient 3
#define  CABLE_ANGLE_CALIB_COEF_4	42        // Cable Angle: Cable angle polynomial coefficient 4

#define PARAM_LIST_CT_CABLE_ANGLE	43	// Count of same FUNCTION_TYPE in preceing list

#define  ENGINE_SENSOR_SEG_CT    	1         // Engine_sensor: Number of black (or white) segments
#define  ENGINE_SENSOR_PRESS_OFFSET	2         // Engine_sensor: Manifold pressure offset         
#define  ENGINE_SENSOR_PRESS_SCALE	3         // Engine_sensor: Manifold pressure  scale (inch Hg)
#define  ENGINE_THERM1_CONST_B   	3         // Engine_sensor: Thermistor param: constant B     
#define  ENGINE_THERM1_R_SERIES  	4         // Engine_sensor: Thermistor param: Series resistor, fixed (K ohms)
#define  ENGINE_THERM1_R_ROOMTMP 	5         // Engine_sensor: Thermistor param: Thermistor room temp resistance (K ohms)
#define  ENGINE_THERM1_REF_TEMP  	6         // Engine_sensor: Thermistor param: Reference temp for thermistor
#define  ENGINE_THERM1_TEMP_OFFSET	7         // Engine_sensor: Thermistor param: Thermistor temp offset correction (deg C)

#define PARAM_LIST_CT_ENGINE_SENSOR	8	// Count of same FUNCTION_TYPE in preceding list


#define READINGS_LIST_COUNT 22
#define  TENSION_READ_ADC_THERM1 	1         // Tension: READING: Filtered ADC for Thermistor 1 
#define  TENSION_READ_ADC_THERM2 	2         // Tension: READING: Filtered ADC for Thermistor 2 
#define  TENSION_READ_CALIB_THERM1	3         // Tension: READING: Calibrated temperaure for Thermistor 1
#define  TENSION_READ_CALIB_THERM2	4         // Tension: READING: Calibrated temperaure for Thermistor 2
#define  TENSION_READ_AD7799_RAW 	5         // Tension: READING: AD7799 #1 raw reading         
#define  TENSION_READ_AD7799_CALIB	6         // Tension: READING: AD7799 #1 calibrated reading  
#define  TENSION_READ_CANMSG_OVFLO	7         // Tension: can_msgovrflow: Count of CAN msg buffer overflow
#define  TENSION_READ_CAN_FIFO1_CT	8         // Tension: error_fifo1ctr: Count of CAN too many time sync msgs in one 1/64th systick interval
#define  TENSION_READ_CAN_TXERR_CT	9         // Tension: can_txerr     : Count of CAN TX error status
#define  TENSION_READ_CAN_RX0ERR_CT	10        // Tension: can_rx0err    : Count of CAN FIFO 0 overruns
#define  TENSION_READ_CAN_RX1ERR_CT	11        // Tension: can_rx1err    : Count of CAN FIFO 1 overruns
#define  CABLE_ANGLE_READ_ADC_THERM1	1         // Cable Angle: READING: Filtered ADC for Thermistor 1
#define  CABLE_ANGLE_READ_ADC_THERM2	2         // Cable Angle: READING: Filtered ADC for Thermistor 2
#define  CABLE_ANGLE_READ_CALIB_THERM1	3         // Cable Angle: READING: Calibrated temperaure for Thermistor 1
#define  CABLE_ANGLE_READ_CALIB_THERM2	4         // Cable Angle: READING: Calibrated temperaure for Thermistor 2
#define  CABLE_ANGLE_READ_AD7799_RAW	5         // Cable Angle: READING: AD7799 #1 raw reading     
#define  CABLE_ANGLE_READ_AD7799_CALIB	6         // Cable Angle: READING: AD7799 #1 calibrated reading
#define  CABLE_ANGLE_READ_CANMSG_OVFLO	7         // Cable Angle: can_msgovrflow: Count of CAN msg buffer overflow
#define  CABLE_ANGLE_READ_CAN_FIFO1_CT	8         // Cable Angle: error_fifo1ctr: Count of CAN too many time sync msgs in one 1/64th systick interval
#define  CABLE_ANGLE_READ_CAN_TXERR_CT	9         // Cable Angle: can_txerr     : Count of CAN TX error status
#define  CABLE_ANGLE_READ_CAN_RX0ERR_CT	10        // Cable Angle: can_rx0err    : Count of CAN FIFO 0 overruns
#define  CABLE_ANGLE_READ_CAN_RX1ERR_CT	11        // Cable Angle: can_rx1err    : Count of CAN FIFO 1 overruns

#define FUNC_BIT_PARAM_COUNT 4
#define  POLL_FUNC_BIT_TENSION   	0x1       // %X                  Function bit: 2nd byte of poll msg: TENSION     
#define  POLL_FUNC_BIT_CABLE_ANGLE	0x2       // %x                  Function bit: 2nd byte of poll msg: CABLE_ANGLE 
#define  POLL_FUNC_BIT_SHAFT_ODO_SPD	0x4       // %x                  Function bit: 2nd byte of poll msg: shaft odometer & speed
#define  POLL_FUNC_BIT_TILT      	0x8       // %x                  Function bit: 2nd byte of poll msg: TILT        

/* TOTAL COUNT OF #defines = 278  */
#endif

/* Test 6 */

