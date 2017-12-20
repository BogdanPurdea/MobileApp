import React from 'react';
import { StyleSheet, Text, View, FlatList, Button } from 'react-native';
import Transaction from './Transaction.js'

const Realm = require('realm');

const TransactionSchema = {
    name: 'Transaction',
    primaryKey: 'id',
    properties: {
        id:  'int',
        category: 'string',
        type: 'string',
        value: 'int'
    }
};

let realm = new Realm({schema: [TransactionSchema]});

const transactions = realm.objects('Transaction');

export class TransactionList extends React.Component {
    static navigationOptions = {
        title: 'List',
    };
    state = {selected: (new Map(): Map<string, boolean>)};
    keyExtractor = (item) => item.id;
    onPressItem = (id: string) => {
        this.setState((state) => {
            const selected = new Map(state.selected);
            selected.set(id, !selected.get(id));
            this.props.navigation.navigate('Item', {transactionId: id, realm: realm});
            return {selected};
        });
    };
    renderItem = ({item}) => (
        <Transaction
            id={item.id}
            onPressItem={this.onPressItem}
            selected={!!this.state.selected.get(item.id)}
            category={item.category}
            type={item.type}
            value={item.value}
        />
    );
    goToAdd(){
        this.props.navigation.navigate('Add', {realm: realm});
    }
    goToDelete(){
        this.props.navigation.navigate('Delete', {realm: realm});
    }
    goToChart(){
        this.props.navigation.navigate('Chart');
    }
    render() {
        return (
            <View style = {styles.container}>
                <Button
                    onPress={() => this.goToChart()}
                    title="Display chart"
                    color="#841584"
                />
                <Text>  Transactions</Text>
                <FlatList
                    data={transactions}
                    extraData={this.state}
                    renderItem={this.renderItem}
                    keyExtractor={this.keyExtractor}
                />
                <Button
                    onPress={() => this.goToAdd()}
                    title="Add"
                    color="#841584"
                />
                <Text>  </Text>
                <Button
                    onPress={() => this.goToDelete()}
                    title="Delete"
                    color="#841584"
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