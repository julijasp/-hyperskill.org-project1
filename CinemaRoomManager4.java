package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

class CinemaRoomManager4 {

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        Cinema cinema = new Cinema();
        cinema.showMenu(rows, seats);

    }

    public static class Cinema {
        public static Scanner scanner = new Scanner(System.in);

        public static int rowNo;
        public static int seatNo;
        public static int currentIncome = 0;
        public static int totalIncome= 0;

        private static List<int[]> soldSeats = new ArrayList<>();

        public static int showMenu(int rows, int seats) {

            int choice;
            int moveNo = 0;
            do {
                System.out.println();
                System.out.println("1. Show the seats\n" +
                        "2. Buy a ticket\n" +
                        "3. Statistics\n" +
                        "0. Exit");
                choice = scanner.nextInt();
                moveNo++;

                switch (choice) {
                    case 1: {
                        if (moveNo == 1) {
                            printSeatingArrangement(rows, seats);
                        } else
                            printReservedSeat(rows, seats);
                        break;
                    }
                    case 2: {
                        printTicketPrice(seats, rows);
                        break;
                    }
                    case 3: {
                        showStatistics(seats, rows);
                        break;
                    }
                    case 0: {
                        break;
                    }
                }
            } while (choice != 0);
            return moveNo;
        }

        public static int printTicketPrice(int rows, int seats) {

            boolean ticketAvailable = true;

            do {
                System.out.println("Enter a row number:");
                Scanner scanner = new Scanner(System.in);
                rowNo = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seatNo = scanner.nextInt();
                ticketAvailable = (getSoldSeat(rowNo, seatNo)==false);
                if (ticketAvailable == false){
                    System.out.println();
                    System.out.println("That ticket has already been purchased!");
                }
                if (rowNo < 0 || rowNo > rows || seatNo < 0 || seatNo > seats) {
                    ticketAvailable = false;
                    System.out.println();
                    System.out.println("Wrong input!");
                }
            } while (ticketAvailable != true);

            soldSeats.add(new int[]{rowNo, seatNo});

            int ticketPrice = 0;
            int totalSeats = rows * seats;
            if (totalSeats <= 60) {
                ticketPrice = 10;
                System.out.println("Ticket price: $" + ticketPrice);


            } else if (totalSeats > 60) {
                if (rowNo <= rows / 2) {
                    ticketPrice = 10;
                    System.out.println("Ticket price: $" + ticketPrice);

                }
                if (rowNo > rows / 2) {
                    ticketPrice = 8;
                    System.out.println("Ticket price: $" + ticketPrice);

                }
            }
            currentIncome += ticketPrice;
            return ticketPrice;
        }

        public static void printSeatingArrangement(int rows, int seats) {

            System.out.println("Cinema:");
            for (int i = 0; i <= rows; i++) {
                for (int j = 0; j <= seats; j++) {
                    if (i == 0 && j == 0) {
                        System.out.print("  ");
                    } else if (i == 0) {
                        System.out.print(j + " ");
                    } else if (j == 0) {
                        System.out.print(i + " ");
                    } else System.out.print('S' + " ");
                }
                System.out.println(" ");
            }
            System.out.println();
        }

        public static void printReservedSeat(int rows, int seats) {

            System.out.println("Cinema:");

            for (int i = 0; i <= rows; i++) {
                for (int j = 0; j <= seats; j++) {
                    if (i == 0 && j == 0) {
                        System.out.print("  ");
                    } else if (i == 0) {
                        System.out.print(j + " ");
                    } else if (j == 0) {
                        System.out.print(i + " ");
                    } else if (getSoldSeat(i, j) == true) {
                        System.out.print('B' + " ");
                    } else System.out.print('S' + " ");
                }
                System.out.println(" ");
            }
        }

        private static boolean getSoldSeat(int rows, int seats) {
            for (int[] seat : soldSeats) {
                if (seat[0] == rows && seat[1] == seats) {
                   return true;
                }
            }
            return false;
        }

        public static void showStatistics(int rows, int seats) {

            System.out.println("Number of purchased tickets: " + soldSeats.size());
            Formatter formatter = new Formatter();
            double totalSeats = rows * seats;
            double soldTicketsPercentage = (soldSeats.size() / (totalSeats) * 100);
            formatter.format("%.2f", soldTicketsPercentage);
            System.out.println("Percentage:" + formatter.toString() + "%");
            System.out.println("Current income: $" + currentIncome);
            int totalIncome = totalIncome(rows, seats);
            System.out.println("Total income: $" + totalIncome);
            System.out.println();

        }

        public static int ticketPrice(int rows, int seats) {
            int ticketPrice = 0;
            int totalSeats = rows * seats;
            if (totalSeats <= 60) {
                ticketPrice = 10;

            } else if (totalSeats > 60) {
                if (rowNo <= rows / 2) {
                    ticketPrice = 10;

                }
                if (rowNo > rows / 2) {
                    ticketPrice = 8;
                }
            }
            return ticketPrice;
        }

        public static int totalIncome(int rows, int seats) {
            int ticketPrice = 0;
            int totalIncome = 0;

            int totalSeats = rows * seats;
            if (totalSeats <= 60) {
                ticketPrice = 10;
                totalIncome = totalSeats * ticketPrice;

            } else if (totalSeats > 60) {
                if (rowNo <= rows / 2) {
                    ticketPrice = 10;
                }
                if (rowNo > rows / 2) {
                    ticketPrice = 8;
                }
                totalIncome = rows/2 *10 * seats + ((rows/2)+1) * 8 * seats;
            }
            return totalIncome;
        }
    }
}

