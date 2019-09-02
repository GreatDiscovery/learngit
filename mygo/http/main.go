package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
)

const (
	_  = iota             // 0
	KB = 1 << (iota * 10) // 1 << (1 * 10)
	MB = 1 << (iota * 10) // 1 << (2 * 10)
	GB = 1 << (iota * 10) // 1 << (3 * 10)
	TB = 1 << (iota * 10) // 1 << (4 * 10)
)
const (
	d = iota
	e
	f
)

func main() {
	res, err := http.Get("http://10.110.25.114:30060")
	if err != nil {
		log.Fatal(err)
	}
	arr, err := ioutil.ReadAll(res.Body)
	res.Body.Close()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("%s", arr)
	fmt.Println("binary\t\tdecimal")
	fmt.Printf("%b\t", KB)
	fmt.Printf("%d\n", KB)
	fmt.Printf("%b\t", MB)
	fmt.Printf("%d\n", MB)
	fmt.Printf("%b\t", GB)
	fmt.Printf("%d\n", GB)
	fmt.Printf("%b\t", TB)
	fmt.Printf("%d\n", TB)
}