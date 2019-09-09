package main

import "fmt"
// 值传递
func Z(z int)  {
	fmt.Println("z", &z)
	fmt.Printf("%p \n", &z)
	z = 0
}
// 引用传递
func Z3(z *int)  {
	fmt.Println(z)
	*z = 0
}
func main() {
	a := 43
	Z3(&a)
	fmt.Println("a -", a)
}
