def postOrderTree(size, inOrder, preOrder):
    if inOrder == []:
        return []
    if size == 1:
        return [preOrder[0]]
    inOrderIndex = inOrder.index(preOrder[0])
    left = postOrderTree(inOrderIndex, inOrder[:inOrderIndex:], preOrder[1:inOrderIndex + 1:])
    right = postOrderTree(size - inOrderIndex, inOrder[inOrderIndex + 1::], preOrder[inOrderIndex + 1::])
    return left + right + [preOrder[0]]

size = int(input())
inOrder = list(map(int, input().split()))
preOrder = list(map(int, input().split()))
postOrder = postOrderTree(size, inOrder, preOrder)
print(" ".join(str(i) for i in postOrder))