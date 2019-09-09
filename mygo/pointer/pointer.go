package main

import "fmt"

func main() {
	a := 53
	b := &a
	fmt.Println("a - ", *b)
}