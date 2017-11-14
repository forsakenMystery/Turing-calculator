package turingcalculator;

import java.util.ArrayList;

/**
 * ماشین تورینگ دارای عملگر های اولیه حسابی با نوار
 * <br>
 * ماشین تورینگ دارای یک نوار از جنس کاراکتر با طول نامحدود است
 *
 * @author FORSAKEN MYSTERY
 */
public class Turing {

    private ArrayList<Character> tape;

    /**
     * عدد وارد شده را تبدیل به یک رشته قابل فهم برای تورینگ می کند
     *
     * @param num یک عدد صحیح
     * @return یک رشته قابل فهم برای ماشین تورینگ
     * <br>
     * به عنوان مثال عدد دو تبدیل به ' ' 1 1 میشود
     * <br>
     * و یا به عنوان مثال عدد چهار تبدیل به ' ' 1 1 1 1 میشود
     */
    public static ArrayList<Character> convertToTape(int num) {
        ArrayList<Character> forReturn = new ArrayList<>();
        while (num-- != 0) {
            forReturn.add('1');
        }
        forReturn.add(' ');
        return forReturn;
    }

    /**
     * نوار ماشین تورینگ را تبدیل به یک عدد صحیح می کند
     *
     * @param tape نوار ماشین تورینگ
     * @return یک عدد صحیح بیانگر عدد موجود در نوار
     */
    public static int convertToInt(ArrayList<Character> tape) {
        int i = 0;
        boolean minus = false;
        while (true) {
            if (tape.get(i) == '-') {
                minus = true;
                i++;
            }
            if (tape.get(i) != ' ') {
                i++;
            } else {
                break;
            }
        }
        if (minus) {
            i *= -1;
            i++;
        }
        return i;
    }

    /**
     * این متد برای این است که عدد که میخواهیم با آن روی ماشین عملیات انجام دهیم
     * را
     * <br>
     * ابتدا با متد convertToTape(int)
     * <br>
     * عدد را تبدیل می کنیم به یک رشته قابل فهم سپس آن را با نوار ماشین ادغام می
     * کنیم و عملیات مورد نظر را انجام می دهیم
     *
     * @param tpedNum عدد تبدیل شده به یک رشته نوار قابل فهم برای ماشین تورینگ
     * @see turingcalculator.Turing#convertToTape(int)
     */
    public void merge(ArrayList<Character> tpedNum) {
        int i = 0;
        while (true) {
            if (tape.get(i) == ' ') {
                break;
            }
            i++;
        }
        tape.set(i, '#');
        i = 0;
        while (true) {
            if (tpedNum.get(i) != ' ') {
                tape.add(tpedNum.get(i));
                i++;
            } else {
                tape.add('$');
                break;
            }
        }
        tape.add(' ');
    }

    /**
     * این متد پس از انجام شدن یک عملیات نوار ماشین تورینگ را آپدیت می کند و آن
     * را بر می گرداند
     *
     */
    protected void updateTape() {
        int j = 0;
        while (true) {
            if (tape.get(j) == ' ' || tape.get(j) == '-' || tape.get(j) == '#' || tape.get(j) == '$') {

                if (tape.get(j) == ' ') {
                    break;
                }
                j++;
                continue;
            } else {
                tape.set(j, '1');
            }
            j++;
        }
        int i = 0;
        int state = 1;
        while (true) {
            if (tape.get(i) == '$') {
                i++;
                break;
            }
            i++;
        }
        while (true) {
            if (state == 1) {
                if (tape.get(i) == ' ') {
                    state = 2;
                    continue;
                }
                if (tape.get(i) == '1') {
                    state = 3;
                    tape.set(i, '2');
                    continue;
                }
                if (tape.get(i) == '-') {
                    state = 4;
                    tape.set(i, '2');
                    continue;
                }
            }
            if (state == 2) {
                if (i == 0) {
                    tape.set(i, ' ');
                    break;
                }
                i--;
            }
            if (state == 3) {
                if (i == 0) {
                    tape.set(i, '3');
                    break;
                }
                i--;
            }
            if (state == 4) {
                if (i == 0) {
                    tape.set(i, '4');
                    state = 5;
                    continue;
                }
                i--;
            }
            if (state == 5) {
                if (tape.get(i) == '2') {
                    state = 6;
                    i++;
                    continue;
                }
                i++;
            }
            if (state == 6) {
                if (tape.get(i) == ' ') {
                    state = 7;
                    continue;
                }
                if (tape.get(i) == '1') {
                    state = 8;
                    tape.set(i, '2');
                    continue;
                }
            }
            if (state == 7) {
                if (tape.get(i) == '4') {
                    i++;
                    tape.set(i, ' ');
                    break;
                }
                i--;
            }
            if (state == 8) {
                if (tape.get(i) == '4') {
                    i++;
                    tape.set(i, '3');
                    break;
                }
                i--;
            }
        }
        i = 0;
        state = 1;
        while (true) {
            if (state == 1) {
                if (tape.get(i) == '2') {
                    state = 2;
                    continue;
                }
                if (tape.get(i) == ' ') {
                    break;
                }
                i++;
            }
            if (state == 2) {
                if (tape.get(i) == '1') {
                    state = 3;
                    tape.set(i, '2');
                    i--;
                    continue;
                }
                if (tape.get(i) == ' ') {
                    i--;
                    state = 4;
                    continue;
                }
                i++;
            }
            if (state == 3) {
                if (tape.get(i) == '3') {
                    state = 5;
                    continue;
                }
                i--;
            }
            if (state == 4) {
                if (tape.get(i) == '3') {
                    state = 6;
                    continue;
                }
                i--;
            }
            if (state == 5) {
                i++;
                tape.set(i, '3');
                state = 1;
                continue;
            }
            if (state == 6) {
                i++;
                tape.set(i, ' ');
                break;
            }
        }
        i = 0;
        while (true) {
            if (tape.get(i) == '3') {
                tape.set(i, '1');
            }
            if (tape.get(i) == ' ') {
                i++;
                break;
            }
            if (tape.get(i) == '4') {
                tape.set(i, '-');
            }
            i++;
        }
        while (true) {
            if (tape.get(i) != ' ') {
                tape.remove(i);
            } else {
                tape.remove(i);
                break;
            }
        }
    }

    /**
     * عدد وارد شده را تبدیل به یک رشته قابل فهم برای ماشین می کند و آن را به
     * عنوا نوار ماشین که نامحدود است در نظر می گیرد
     *
     * @param num مقدار اولیه در نوار ماشین تورینگ
     * @see turingcalculator.Turing#convertToTape(int)
     */
    public Turing(int num) {
        tape = Turing.convertToTape(num);
    }

    /**
     *
     * @return نوار تورینگ را به یک عدد صحیح تبدیل می کند
     * @see turingcalculator.Turing#convertToInt(java.util.ArrayList)
     */
    public int toInt() {
        return convertToInt(tape);
    }

    /**
     * این متد هرچه 1 را قبل از $ ببیند به بعد از آن انتقال می دهد سپس با
     * استفاده از متد
     * <br>
     * updateTape()
     * <br>
     * نوار ماشین تورینگ را آپدیت می کنیم که حاصل جمع عملیات میشود
     *
     * @param num عدد وارد شده را با موجودی نوار ماشبن جمع می کند
     * @see turingcalculator.Turing#updateTape()
     */
    public void add(int num) {
        merge(Turing.convertToTape(num));

        int i = 0;
        int state = 1;
        while (true) {
            /**
             *
             */
            if (tape.get(i) == '1' && state == 1) {
                tape.set(i++, '2');
                state = 2;
            }
            if (tape.get(i) == '#' && state == 1) {
                i++;
            }
            if (tape.get(i) == '$' && state == 1) {
                break;
            }
            if (state == 2) {
                if (tape.get(i) != ' ') {
                    i++;
                } else {
                    tape.set(i, '1');
                    tape.add(' ');
                    state = 3;
                }
            }
            if (state == 3) {
                if (tape.get(i) != '2') {
                    i--;
                } else {
                    i++;
                    state = 1;
                }
            }
        }
        updateTape();
    }

    /**
     * یک های قبل از # را علامت میزند و به تعداد یک های بعد از شارپ بعد از $ یک
     * میگذاریم
     * <br>
     * این عمل را انقدر تکرار می کنیم تا سمت چپ # یک باقی نماند نوار ماشین
     * تورینگ را با استفاده از متد
     * <br>
     * updateTape()
     * <br>
     * آپیدت می کنیم تا حاصل ضرب عملیات در نوار قرار داده شود
     *
     * @param num عدد وارد شده را با موجودی نوار ماشین جمع می کند
     * @see turingcalculator.Turing#updateTape()
     */
    public void multiply(int num) {
        merge(Turing.convertToTape(num));
        int i = 0;
        int state = 1;
        while (true) {
            if (tape.get(i) == '1' && state == 1) {
                tape.set(i, '2');
                state = 2;
                continue;
            }
            if (tape.get(i) == '#' && state == 1) {
                break;
            }
            if (state == 2) {
                if (tape.get(i++) == '#') {
                    state = 3;
                    continue;
                }
            }
            if (state == 3 && tape.get(i) == '$') {
                break;
            }
            if (state == 3 && tape.get(i) == '1') {
                state = 4;

                continue;
            }
            if (state == 4) {
                if (tape.get(i) == '1') {
                    tape.set(i, '3');
                    state = 5;

                    continue;
                } else {
                    state = 6;

                    continue;
                }
            }
            if (state == 5) {
                if (tape.get(i) != ' ') {
                    i++;

                    continue;
                } else {
                    tape.set(i, '1');
                    tape.add(' ');
                    state = 7;

                    continue;
                }
            }
            if (state == 7) {
                if (tape.get(i) != '3') {
                    i--;

                    continue;
                } else {
                    i++;
                    state = 4;

                    continue;
                }
            }
            if (state == 6) {
                if (tape.get(i) != '#') {
                    if (tape.get(i) == '3') {
                        tape.set(i, '1');
                    }
                    i--;
                    continue;
                } else {
                    state = 8;

                    continue;
                }
            }
            if (state == 8) {
                if (tape.get(i) != '2') {
                    i--;

                    continue;
                } else {
                    i++;
                    state = 1;

                    continue;
                }
            }
        }

        updateTape();
    }

    /**
     * به صورت کلی یک های دو سمت # را علامت میزنیم و تا زمانی که یک سمت تمام یک
     * ها علامت زده شد یک های علامت نزده شده را به بعد از $ منتقل می کنیم سپس
     * نوار را آپدیت می کنیم
     *
     * @param num عدد وارد شده را از موجودی نوار ماشین جمع می کند
     * @see turingcalculator.Turing#updateTape()
     */
    public void subtract(int num) {
        merge(Turing.convertToTape(num));
        boolean minus = false;
        int i = 0;
        int state = 1;
        while (true) {
            if (state == 1 && tape.get(i) == '1') {
                tape.set(i, '2');
                i++;
                state = 2;
                continue;
            }
            if (state == 1 && tape.get(i) == '#') {
                minus = true;
                state = 5;
                continue;
            }
            if (state == 2) {
                if (tape.get(i) != '#') {
                    i++;
                    continue;
                } else {
                    state = 3;
                    i++;
                    continue;
                }
            }
            if (state == 3) {
                if (tape.get(i) == '3') {
                    i++;
                    continue;
                } else if (tape.get(i) == '1') {
                    tape.set(i, '3');
                    state = 4;
                    continue;
                } else {
                    minus = false;
                    state = 5;
                    continue;
                }
            }
            if (state == 4) {
                if (tape.get(i) != '2') {
                    i--;
                    continue;
                } else {
                    i++;
                    state = 1;
                    continue;
                }
            }
            if (state == 5) {
                if (tape.get(i) != ' ') {
                    i++;
                    continue;
                } else {
                    state = 6;
                    continue;
                }
            }
            if (state == 6) {
                if (minus) {
                    tape.set(i, '-');
                } else {
                    tape.set(i, '1');
                }
                tape.add(' ');
                break;
            }
        }
        i = 0;
        state = 0;
        while (true) {
            if (tape.get(i) == '$' && state == 0) {
                break;
            }
            if (state == 0 && tape.get(i) != '1') {
                i++;
                continue;
            }
            if (state == 0 && tape.get(i) == '1') {
                tape.set(i, '4');
                state = 1;
                continue;
            }
            if (state == 1) {
                if (tape.get(i) != ' ') {
                    i++;
                    continue;
                } else {
                    tape.set(i, '1');
                    tape.add(' ');
                    state = 2;
                    continue;
                }
            }
            if (state == 2) {
                if (tape.get(i) != '4') {
                    i--;
                    continue;
                } else {
                    state = 0;
                }
            }
        }
        if (minus) {
            while (true) {
                if (tape.get(i++) != ' ') {
                    continue;
                } else {
                    i--;
                    i--;
                    break;
                }
            }
        }
        if (tape.get(i) == '-') {
            tape.remove(i);
            i++;
        }
        updateTape();
    }

    /**
     * ایتدا چک می کنیم سمت چپ بزرگ تر باشد اگر سمت چپ بزرگت بود یک های بعد از
     * شارپ را علامت میزنیم تا تمام شوند وقتی تمام شدند یک یک بعد از $ می گذاریم
     * <br>
     * این عمل را آنقدر انجام میدهیم که تعداد یک های سمت چپ از تعداد یک های سمت
     * راست کمتر بشود
     * <br>
     * تعداد یک های بعد از $ حاصل عملیات تقسیم است نوار را آپدیت می کنیم و حاصل
     * را در آن میگذاریم
     *
     * @param num عدد وارد شده را بر موجودی نوار ماشین تقسیم می کند
     * @see turingcalculator.Turing#updateTape()
     */
    public void division(int num) {
        merge(Turing.convertToTape(num));
        int state = 1;
        int i = 0;
        boolean DO_continue = true;
        while (true) {
            if (state == 1) {
                if (tape.get(i) == '#') {
                    state = 2;
                    i++;
                    continue;
                }
            }
            if (state == 2) {
                if (tape.get(i) == '$') {
                    DO_continue = false;
                }
                break;
            }
            i++;
        }
        while (true) {
            if (!DO_continue) {
                break;
            }
            state = 1;
            i = 0;
            while (true) {

                if (state == 1) {
                    if (tape.get(i) == '1') {
                        tape.set(i, '2');
                        state = 2;
                        continue;
                    } else if (tape.get(i) == '#') {
                        state = 3;
                        continue;
                    }
                    i++;
                }
                if (state == 2) {
                    if (tape.get(i) == '#') {
                        state = 4;
                        continue;
                    }
                    i++;
                }
                if (state == 3) {
                    if (tape.get(i) == '1') {
                        DO_continue = false;
                        break;
                    }
                    if (tape.get(i) == '$') {
                        break;
                    }
                    i++;
                }
                if (state == 4) {
                    if (tape.get(i) == '1') {
                        tape.set(i, '3');
                        state = 5;
                        continue;
                    }
                    if (tape.get(i) == '$') {
                        DO_continue = true;
                        break;
                    }
                    i++;
                }
                if (state == 5) {
                    if (tape.get(i) == '2') {
                        state = 1;
                        continue;
                    }
                    i--;
                }
            }
            i = 0;
            while (true) {
                if (tape.get(i) == ' ') {
                    break;
                }
                if (tape.get(i) == '2' || tape.get(i) == '3') {
                    tape.set(i, '1');
                }
                i++;
            }
            state = 1;
            i = 0;
            if (DO_continue) {
                while (true) {
                    if (state == 1) {
                        if (tape.get(i) == '#') {
                            state = 2;
                            continue;
                        }
                        i++;
                    }
                    if (state == 2) {
                        if (tape.get(i) == '1') {
                            tape.set(i, '4');
                            state = 3;
                            continue;
                        }
                        if (tape.get(i) == '$') {
                            state = 4;
                            continue;
                        }
                        i++;
                    }
                    if (state == 3) {
                        if (i == 0) {
                            state = 5;
                            continue;
                        }
                        i--;
                    }
                    if (state == 4) {
                        if (tape.get(i) != ' ') {
                            i++;
                        } else {
                            tape.set(i, '1');
                            tape.add(' ');
                            state = 6;
                            continue;
                        }
                    }
                    if (state == 5) {
                        if (tape.get(i) == '1') {
                            tape.set(i, '5');
                            state = 1;
                            continue;
                        }
                        i++;
                    }
                    if (state == 6) {
                        if (tape.get(i) == '$') {
                            state = 7;
                            continue;
                        }
                        i--;
                    }
                    if (state == 7) {
                        if (tape.get(i) == '#') {
                            break;
                        }
                        if (tape.get(i) == '4') {
                            tape.set(i, '1');
                        }
                        i--;
                    }
                }
            } else {
                break;
            }
        }

        updateTape();
    }

    /**
     *
     * @return نوار ماشین را بر می گرداند
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tape.size(); i++) {
            result.append(tape.get(i));
        }
        result.append("\n");
        return result.toString();
    }

}
