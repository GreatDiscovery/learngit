package main

import (
	"fmt"
)

func main(){
	var Myname  = "gavin"
	fmt.Println("hello world")
	fmt.Println(42)
	// 2进制
	fmt.Printf("%d - %b \n", 42, 42)
	// 16进制
	fmt.Printf("%d \t %b \t %#X \n", 42, 42, 42)
	// utf-8
	fmt.Printf("%d \t %b \t %x \t %q \n", 42, 42, 42, 42)

	fmt.Println(Myname)

	a := 10
	// T type
	fmt.Printf("%v \n", a)
	fmt.Printf("%T \n", a)

	var message string
	message = "hello world"
	var  b, c int =  2, 3
	fmt.Println(message, a, b, c)
}
