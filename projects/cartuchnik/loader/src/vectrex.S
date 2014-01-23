	.area	.text
	.globl _Reset0Ref
_Reset0Ref:
	pshs	a, b, x, dp
	lda	#0xd0		; required input values for the positd executive subroutine:
	tfr	a, dp		; DP = 0xd0
	jsr	0xf354
	puls	a, b, x, dp, pc

	.globl _Wait_Recal 
_Wait_Recal:
	pshs	a, b, x, dp
	lda	#0xd0		; required input values for the positd executive subroutine:
	tfr	a, dp		; DP = 0xd0
	jsr	0xf192
	puls	a, b, x, dp, pc

	.globl _Moveto
_Moveto:
; input from C compiler:
; - first 8-bit argument x in register b
; - second 8-bit argument y on stack
	pshs	a, b, dp	; save registers
	lda	#0xd0		; required input values for the positd executive subroutine:
	tfr	a, dp		; DP = 0xd0
	lda	5, s		; a = y coordinate
	jsr	0xf2fc		; b = y coordinate
	puls	a, b, dp, pc	; restore registers and PC (=return)

	.globl _Intensity
_Intensity:
; input from C compiler:
; - 8-bit argument in register b
	pshs	a, b, dp	; save registers
	lda	#0xd0		; required input values for the intens executive subroutine:
	tfr	a, dp		; DP = 0xd0
	tfr	b, a		; a = intensity
	jsr	0xf2ab
	puls	a, b, dp, pc	; restore registers and PC (=return)

	.globl _diffab
_diffab:
; input from C compiler:
; - first 8-bit argument x in register b
; - second 8-bit argument y on stack
	pshs	a, b, x, dp	; save registers
	lda	#0xd0		; required input values for the diffab executive subroutine:
	tfr	a, dp		; DP = 0xd0
	lda	7, s		; a = y coordinate
	jsr	0xf3df		; b = y coordinate
	puls	a, b, x, dp, pc	; restore registers and PC (=return)

	;
	; void Draw_Vlp_b(uint8_t* path, uint8_t scale, uint8_t zeroskip)
	;
	.globl _Draw_VLp_b
_Draw_VLp_b:
; x = 16-bit pointer to vector list
; b = 8-bit scale factor
	pshs	a, b, x, dp	; save registers
	lda 7, s
	sta 0xc824
	lda #0xd0
	jsr 0xf40E		; Draw_VLp_b
	puls	a, b, x, dp, pc	; restore registers and PC (=return)

	.globl _Draw_VLp_7f
_Draw_VLp_7f:

; input from C compiler:
; - 1st 16-bit argument in register x: pointer to packet style list
; - 2nd 8-bit argument in register b: zskip: 0 = skip zeroing integrator, 1 = zero integrators
	pshs	a, b, x, dp	; save registers
	lda	#0xd0		; required input values for the executive subroutine
	tfr	a, dp		; DP = 0xd0
	stb	0xc824		; ZSKIP
	jsr	0xf408
	puls	a, b, x, dp, pc	; restore registers and PC (=return)

	.globl _Draw_VLp_scale
_Draw_VLp_scale:
; input from C compiler:
; - 1st 16-bit argument in register x: pointer to packet style list
; - 2nd 8-bit argument in register b: zskip: 0 = skip zeroing integrator, 1 = zero integrators
	pshs	a, b, x, dp	; save registers
	lda	#0xd0		; required input values for the executive subroutine
	tfr	a, dp		; DP = 0xd0
	stb	0xc824		; ZSKIP
	jsr	0xf40c
	puls	a, b, x, dp, pc	; restore registers and PC (=return)

	.globl _Print_Str_d
_Print_Str_d:
; input:
;	byte x in register b
;	byte y on stack
;   string pointer in register x
	pshs a, b, x, dp
	lda #0xd0
	tfr a, dp
	exg x, u
	lda 7, s
	jsr 0xf37a
	puls a, b, x, dp, pc

_Print_Str_hwyx:
; input:
;   parameter block
	pshs a, b, x, dp
	exg x, u
	jsr 0xf373
	puls a, b, x, dp, pc

	.end