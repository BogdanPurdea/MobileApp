import React from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Transaction from './Transaction.js'

t1 = new Transaction(1,'Salary','Income',5000);
t2 = new Transaction(2,'School','Expense',1000);
t3 = new Transaction(3,'Food','Expense',100);

const transactions = [t1,t2,t3];
const extractKey = ({id}) => id;

export class TransactionList extends React.Component {
    static navigationOptions = {
        title: 'List',
    };

    onTransaction=(transaction)=>{
        this.props.navigation.navigate('Item', 'Transaction '+transaction.id, transaction);
    };
    render() {
        return (
            <View style = {styles.container}>
                <Text>Transactions</Text>
                <FlatList
                    data={transactions}
                    renderItem={({item}) => item.render()}
                    keyExtractor={extractKey}
                    onPress={({item})=>this.onTransaction(item)}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        marginTop: 30,
        flex: 1,
    },
});