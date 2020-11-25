// 1) FT

int i = 0;
int j = 1;
int[] failureTable = new int[m];


failureTable[0] = 0;

while (j < m) {

    if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
        failureTable[j] = i + 1;
        i++;
        j++;
    } else {
        if (i == 0) {
            failureTable[j] = 0;
            j++;
        } else {
            i = failureTable[i - 1];
        }
    }
}


// 2) KMP
int j = 0;
int i = 0;

int[] failureT = buildFailureTable(pattern, comparator);
while (i - (j + 1) < (n - m)) {
    if (comparator.compare(text.charAt(i), pattern.charAt(j)) == 0) {

        i++;
        j++;

        if (j < m) {
            continue;
        } else {
            list.add(i - j);
        }

    } else if (j == 0) {
        i++;
        continue;
    }

    j = failureT[j - 1];
}
return list;


// 3) BM
Map<Character, Integer> table = buildLastTable(pattern);
int i = 0;
while (i <= (n - m)) {

    int j = m - 1;

    while ((j >= 0) && (comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0)) {
        j = j - 1;
    }

    if (j == -1) {

        list.add(i);
        i++;
        continue;
    } else {
        
        int shift = table.getOrDefault(text.charAt(i + j), -1);

        if (shift < j) {

            i = i + j - shift;
        } else {
            i++;
        }
    }
}
return list;

// RK

