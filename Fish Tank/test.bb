AppTitle "Computer fish tank"
Graphics 640,480,0,1
SetBuffer BackBuffer()


Type fish
Field x,y
Field vx#,vy#
Field frame
Field shapenum
Field shape
End Type

FlushKeys
FlushMouse
MoveMouse 0,0

Global tank1 = LoadImage("tank1.bmp")
Global tank2 = LoadImage("tank2.bmp")

Global one = LoadAnimImage("fish 1.bmp",62,25,0,2)
Global two = LoadAnimImage("fish 2.bmp",72,20,0,2)
Global three = LoadAnimImage("fish 3.bmp",88,61,0,2)


SeedRnd MilliSecs()

For i = 1 To 3
fish.fish = New fish
	fish\x = Rnd(100,400)
	fish\y = Rnd(50,420)
	fish\vx = Rnd(.5,2)
	fish\vy = Rnd(.5,2)
	fish\frame = Rnd(0,1)
	fish\shapenum = i
	
	If fish\frame = 1
		fish\vx = - fish\vx
	EndIf 
	
If fish\shapenum = 1
	fish\shape = one
ElseIf fish\shapenum = 2
 	fish\shape = two
Else
	fish\shape = three
EndIf
 
Next 



While MouseX() = 0 And MouseY() = 0; And Not GetKey() = True
drawstuff()
movefish()
checkcollision()
changedirection()
Flip
Cls
Wend

Function drawstuff()
DrawImage tank1,0,0

For fish.fish = Each fish

DrawImage fish\shape,fish\x,fish\y,fish\frame

Next 

End Function 

Function movefish()
For fish.fish = Each fish
	fish\x = fish\x + fish\vx
	fish\y = fish\y + fish\vy
Next 
End Function 

Function checkcollision()
SeedRnd MilliSecs()
For fish.fish = Each fish
	If fish\x <= 0
		fish\frame = 0
		fish\vx = Rnd(.5,2)
		fish\vy = Rnd(.5,2)
	ElseIf fish\x + ImageWidth(fish\shape) >=640
		fish\frame = 1
		fish\vx = Rnd(-2,-.5)
		fish\vy = Rnd(.5,2)
	ElseIf fish\y <= 0
		fish\vy = Rnd(.5,2)
	ElseIf fish\y + ImageHeight(fish\shape) >=480
		fish\vy = Rnd (-2,-.5)
	EndIf 
Next
End Function 

Function changedirection()
For fish.fish = Each fish
	SeedRnd MilliSecs()
	change = Rnd(200)
		If change = 90
			If fish\frame = 0
				fish\frame = 1
				fish\vx = -fish\vx
			EndIf 
		EndIf 
		If change = 110
			fish\vy = - fish\vy
		EndIf 
Delay(10)		
Next
End Function 